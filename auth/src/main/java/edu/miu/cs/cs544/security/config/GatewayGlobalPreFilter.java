package edu.miu.cs.cs544.security.config;

import edu.miu.cs.cs544.service.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GatewayGlobalPreFilter implements GlobalFilter {

    @Value("${application.paths.noAuthentication}")
    private List<String> noAuthPaths;
    @Autowired
    private JwtService jwtService;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("######################### Global Pre Filter executed @@@@@@@@@@@@@@@@@@");

        ServerHttpRequest request = exchange.getRequest();
        System.out.println("############# " + request.getPath().value());
        System.out.println("############# " + request.getMethod().toString());

        if(!isPathRequireAuthentication(request.getMethod().toString(), request.getPath().value())){
            return chain.filter(exchange);
        }
        String token = getAuthHeader(request);
        if (null!=token && token.startsWith("Bearer ") && token.length() > 7) {
            token = token.substring(7, token.length());
        }
        if(jwtService.isTokenValid(token)) {
            Claims claims = jwtService.extractAllClaims(token);
            Integer userId = (Integer) claims.get("userId");
            Boolean admin = (Boolean) claims.get("admin");

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
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        try {
            return request.getHeaders().getOrEmpty("Authorization").get(0);
        }catch (Exception e){
            return null;
        }
    }

    private boolean isPathRequireAuthentication(String method, String path){
        String pathAndMethod = (method + "." + path).toUpperCase();
        boolean result = true;
        for(String noAuthPath: noAuthPaths){
            noAuthPath = noAuthPath.toUpperCase();
            System.out.println(noAuthPath + " - " + pathAndMethod);
            if(pathAndMethod.startsWith(noAuthPath)){
                System.out.println(pathAndMethod + " requires no authentication");
                result = false;
            }
        }
        return result;
    }
}
