package com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.DTO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ResponceTokenDTO {
    private String username;
    private String message;
    private String status;
    private String role;
}
