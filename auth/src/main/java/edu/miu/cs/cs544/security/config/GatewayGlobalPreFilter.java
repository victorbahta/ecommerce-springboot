package edu.miu.cs.cs544.security.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class GatewayGlobalPreFilter implements GlobalFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("######################### Global Pre Filter executed @@@@@@@@@@@@@@@@@@");
        Set<Map.Entry<String, List<String>>> headerSet = exchange.getRequest().getHeaders().entrySet();
        Iterator<Map.Entry<String, List<String>>> headerItr = headerSet.iterator();
        while(headerItr.hasNext()){
            Map.Entry<String, List<String>> header = headerItr.next();
            System.out.println(header.getKey() + " - " + header.getValue());
        }


        return chain.filter(exchange);
    }
}
