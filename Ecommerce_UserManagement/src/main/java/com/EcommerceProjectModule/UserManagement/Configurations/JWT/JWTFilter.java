package com.EcommerceProjectModule.UserManagement.Configurations.JWT;

import com.EcommerceProjectModule.UserManagement.Services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;




@Component
public class JWTFilter {// extends OncePerRequestFilter {


//    private   JWTService jwtService ;
//
//    private UserService userService;
//
//    public JWTFilter(JWTService jwtService, UserService userService){
//        this.jwtService = jwtService;
//        this.userService= userService;
//    }
//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
//        if (request.getServletPath().contains("/auth")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        //1 define
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        //2 check if token is available or not
//        if(authHeader == null && !authHeader.startsWith("Bearer ")){
//            filterChain.doFilter(request,response);
//            return;
//        }
//        //3 extract jwt
//        jwt = authHeader.substring(7);
//
//        //4 extarct username from tokenx
//        String username = jwtService.extractUsername(jwt);
//
//
//        //5  save in secutiry context
//        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails = userService.loadUserByUsername(username);
//            if(jwtService.isJWTValid(jwt, userDetails)){
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
}
