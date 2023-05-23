package edu.miu.cs.cs544.feign;


import edu.miu.cs.cs544.dto.CustomerCredentialDto;
import edu.miu.cs.cs544.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("AUTHENTICATIONSERVICE")
public interface AuthService {

    @PostMapping(value = "/users")
    public void saveCredential(@RequestBody CustomerCredentialDto customerCredentialDto);
}
