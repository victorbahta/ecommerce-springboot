package edu.miu.cs.cs544.feign;

import edu.miu.cs.cs544.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${api-client.name}", url = "${api-client.url}", configuration = FeignConfig.class)
public interface ProductServiceFeignClient {
}