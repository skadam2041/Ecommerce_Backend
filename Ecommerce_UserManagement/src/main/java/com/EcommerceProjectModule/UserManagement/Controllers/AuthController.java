package com.EcommerceProjectModule.UserManagement.Controllers;


import com.EcommerceProjectModule.UserManagement.DTOs.*;
import com.EcommerceProjectModule.UserManagement.Models.User;
import com.EcommerceProjectModule.UserManagement.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ResponceToUser> signUp(@RequestBody UserSignupRequest request) {


        try {
            if(request.getUsername() == null || request.getEmail() == null || request.getPassword() == null || request.getRole()== null){
                throw new RuntimeException("Please fill all the fields");
            }
            User user = authService.signUp(request);
            ResponceToUser responceToUser = new ResponceToUser();
            responceToUser.setMessage("User created successfully");
            responceToUser.setStatus("Active");
            responceToUser.setUsername(user.getUsername());
            responceToUser.setEmail(user.getEmail());
            responceToUser.setRoles(user.getRoles());

            MultiValueMapAdapter<String,String> headers = new MultiValueMapAdapter<String, String>(new HashMap<>());
            return new ResponseEntity<>(responceToUser,headers, HttpStatus.OK);
        }catch (Exception e){
            ResponceToUser responceToUser = new ResponceToUser();
            responceToUser.setMessage("User creation failed : " + e.getMessage());
            return new ResponseEntity<>(responceToUser,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponceToUser> login(@RequestBody UserLoginRequest request) {
        try {
            User user = authService.login(request.getUsername(), request.getPassword());
            if (user != null) {
                ResponceToUser responceToUser = new ResponceToUser();
                responceToUser.setMessage("User loggedin successfully");
                responceToUser.setStatus("Active");
                responceToUser.setUsername(user.getUsername());
                responceToUser.setEmail(user.getEmail());
                responceToUser.setRoles(user.getRoles());
                MultiValueMapAdapter<String,String> headers = new MultiValueMapAdapter<String, String>(new HashMap<>());
                headers.add(HttpHeaders.SET_COOKIE, "auth-token:" + user.getToken());
                return new ResponseEntity<>(responceToUser,headers, HttpStatus.OK);
            }
        }catch (Exception e){
            ResponceToUser responceToUser = new ResponceToUser();
            responceToUser.setMessage("User logged in failed : " + e.getMessage());
            responceToUser.setStatus("Inactive");
            return new ResponseEntity<>(responceToUser,HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody UserLogoutRequesst request) {
        authService.logout(request.getToken(), request.getUserId());
        return new  ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/validate")
    public ResponseEntity<ResponceToUser> validateToken(@RequestBody ValidateTokenRequestDto request) {

        try {
            Boolean sessionStatus = authService.validate(request.getToken(), request.getUsername());

            if(sessionStatus){
                ResponceToUser session = new ResponceToUser();
                session.setMessage("Session is valid");
                session.setStatus("Active");
                session.setUsername(request.getUsername());
                MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<String, String>(new HashMap<>());
                headers.add(HttpHeaders.SET_COOKIE, "auth-token:" + request.getToken());
                return new ResponseEntity<>(session, headers, HttpStatus.OK);
            }
        }catch (Exception e){
            ResponceToUser session = new ResponceToUser();
            session.setMessage("Session is invalid");
            session.setStatus("false");
            return new ResponseEntity<>(session, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
