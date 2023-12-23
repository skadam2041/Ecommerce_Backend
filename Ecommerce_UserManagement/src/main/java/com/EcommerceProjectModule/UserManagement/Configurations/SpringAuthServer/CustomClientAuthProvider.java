package com.EcommerceProjectModule.UserManagement.Configurations.SpringAuthServer;

import com.EcommerceProjectModule.UserManagement.Configurations.SpringAuthServer.Models.Client;
import com.EcommerceProjectModule.UserManagement.Configurations.SpringAuthServer.Repos.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomClientAuthProvider   implements AuthenticationProvider {
    @Autowired
    private ClientRepository  clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Client client = clientRepository.findByClientId(username).get();

        if(passwordEncoder.matches(password,client.getClientSecret())){
            return new UsernamePasswordAuthenticationToken(client.getClientId(),client.getClientSecret());
        }else{
            throw  new BadCredentialsException("Invalid Credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
