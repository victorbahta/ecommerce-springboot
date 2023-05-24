package edu.miu.cs;

import edu.miu.cs.services.ProductService;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.logging.Logger;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ProductApplication {


	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
