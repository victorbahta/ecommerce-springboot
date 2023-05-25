package edu.miu.cs.cs544.security;

import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.repository.UserRepository;
import edu.miu.cs.cs544.service.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Component
public class GatewayGlobalPreFilter implements GlobalFilter {

    @Value("${application.paths.noAuthentication}")
    private List<String> noAuthPaths;

    @Value("${application.paths.requireAdmin}")
    private List<String> requireAdminPaths;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        System.out.println("######################### Global Pre Filter executed @@@@@@@@@@@@@@@@@@");

        ServerHttpRequest request = exchange.getRequest();
//        System.out.println("############# " + request.getPath().value());
//        System.out.println("############# " + request.getMethod().toString());

        if(isPathRequireNoAuthentication(request.getMethod().toString(), request.getPath().value())){
            return chain.filter(exchange);
        }
        String token = getAuthHeader(request);
        if (null!=token && token.startsWith("Bearer ") && token.length() > 7) {
            token = token.substring(7, token.length());
        }
        if(jwtService.isTokenValid(token)) {
            Claims claims = jwtService.extractAllClaims(token);
            Integer userId = (Integer) claims.get("userId");


            if(null == userId){
                return this.onError(exchange, "UNAUTHORIZED ACCESS, NO USERID or ADMIN CLAIMS IN TOKEN", HttpStatus.UNAUTHORIZED);
            }
            boolean admin = false;
            Optional<User> optionalUser = userRepository.findById(userId);
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                admin = user.isAdmin();
            }else{
                return this.onError(exchange, "UNAUTHORIZED ACCESS, USERID NOT FOUND", HttpStatus.UNAUTHORIZED);
            }

            if(!admin && isPathRequireAdminAccess(request.getMethod().toString(), request.getPath().value())){
                return this.onError(exchange, "THIS FEATURE REQUIRES ADMIN PERMISSION", HttpStatus.FORBIDDEN);
            }

             request = request.mutate()
                    .header("userId", String.valueOf(userId))
                    .header("admin", String.valueOf(admin))
                    .build();

            ServerWebExchange exchange1 = exchange.mutate().request(request).build();
            return chain.filter(exchange1);
        }else {
            return this.onError(exchange, "UNAUTHORIZED ACCESS", HttpStatus.UNAUTHORIZED);
        }
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        byte[] bytes = err.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        response.setStatusCode(httpStatus);
        response.writeWith(Flux.just(buffer));
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        try {
            return request.getHeaders().getOrEmpty("Authorization").get(0);
        }catch (Exception e){
            return null;
        }
    }

    private boolean isPathRequireNoAuthentication(String method, String path){
        return isPathsMatched(method, path, noAuthPaths);
    }

    private boolean isPathRequireAdminAccess(String method, String path){
        return isPathsMatched(method, path, requireAdminPaths);
    }

    private boolean isPathsMatched(String method, String path, List<String> regexPaths){
        String pathAndMethod = (method + "." + path).toLowerCase();
        boolean result = false;
        for(String regexPath: regexPaths){
            regexPath = regexPath.toLowerCase();
//            System.out.println(pathAndMethod + " - " + regexPath);
            if(pathAndMethod.matches(regexPath)){
                result = true;
//                System.out.println(pathAndMethod + " - " + regexPath + " - " + result);
                break;
            }

        }
        return result;
    }
}
