package edu.miu.cs.cs544.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class FeignConfig {

	@Value("${api-client.username}")
	private String username;

	@Value("${api-client.password}")
	private String password;

	@Bean
	BasicAuthRequestInterceptor unityAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor(username, password);
	}
}
