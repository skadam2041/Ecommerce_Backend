package com.EcommerceProjectModule.ProductCatalog.ProxyServiceImplementation.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        configurer -> configurer
                                .requestMatchers(HttpMethod.GET, "/products/categories").permitAll()
                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/search").permitAll()
                                .anyRequest().authenticated()
                );
//        http.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//        http.oauth2Login(oauth2login ->
//                oauth2login.loginPage("/oauth2/authorization/product-client-oidc"))
//                .oauth2Client(Customizer.withDefaults());

        http.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
        http.oauth2Login(Customizer.withDefaults());
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .anyRequest().authenticated()
//                )
//                .oauth2ResourceServer((oauth2) -> oauth2
//                        .jwt(Customizer.withDefaults())
//                );
//        return http.build();
//    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation("http://127.0.0.1:9090");
    }



}
