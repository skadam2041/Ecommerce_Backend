package com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Security;

import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.DTO.RequestTokenDTO;
import com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.DTO.ResponceTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class JwtValidator {

    @Autowired
    private RestTemplateBuilder  restTemplateBuilder;

    public ResponceTokenDTO validateToken(String token){
        RestTemplate restTemplate = restTemplateBuilder.build();
        String validatingUrl = "http://localhost:9090/auth/validate";
        System.out.println("Validating url: " + validatingUrl);
        RequestTokenDTO  tokenDTO = new RequestTokenDTO();
        tokenDTO.setToken(token);
        ResponseEntity<ResponceTokenDTO>  responceTokenDTO =  restTemplate.postForEntity(validatingUrl,tokenDTO,ResponceTokenDTO.class);
        System.out.println("ResponceTokenDTO: " + responceTokenDTO);
        return responceTokenDTO.getBody();
    }



}
