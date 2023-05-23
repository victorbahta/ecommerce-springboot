package edu.miu.cs.cs544.feign;

import edu.miu.cs.cs544.config.FeignConfig;
import edu.miu.cs.cs544.contract.CustomerDto;
import edu.miu.cs.cs544.contract.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "${api-client.product.name}", url = "${api-client.product.url}", configuration = FeignConfig.class)
public interface ProductServiceFeignClient {

    @GetMapping(value = "/products/{productId}")
    Optional<ProductDto> findProductById(@PathVariable("productId") Integer productId);
}