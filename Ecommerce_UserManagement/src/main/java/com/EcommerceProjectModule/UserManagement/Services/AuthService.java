package com.EcommerceProjectModule.UserManagement.Services;

import com.EcommerceProjectModule.UserManagement.Configurations.JWT.JWTService;
import com.EcommerceProjectModule.UserManagement.DTOs.UserSignupRequest;
import com.EcommerceProjectModule.UserManagement.Models.Role;
import com.EcommerceProjectModule.UserManagement.Models.User;
import com.EcommerceProjectModule.UserManagement.Repositories.RoleRepository;
import com.EcommerceProjectModule.UserManagement.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@Service
@Transactional
public class AuthService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;
    private JWTService  jwtService;

    public AuthService(UserRepository userRepository ,BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository,JWTService jwtService ) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.jwtService = new JWTService();
    }






    public User signUp(UserSignupRequest request) {

        User dbUser = userRepository.findByUsername(request.getUsername());

        if(dbUser != null && dbUser.getUsername().equals(request.getUsername()) && dbUser.getEmail().equals(request.getEmail()) ){
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setCreated_at(new Date());


        Role role = roleRepository.findByRoleName(request.getRole());

        if(role == null) {
            role = new Role();
            role.setRoleName(request.getRole());
            role.getUsers().add(user);
            roleRepository.save(role);
        }


        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        user.setStatus("ACTIVE");

        User savedUser = userRepository.save(user);


        return savedUser;
    }


    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        if(user.getUsername().equals(username) && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            //generate token
            String token = jwtService.generateToken(user);
            user.setToken(token);   //set token to user
            User dbUser = userRepository.save(user);
            return dbUser;
        }
        throw  new RuntimeException("Invalid credentials");
    }

    public void logout(String token, Long userId) {
        return;
    }

    public Boolean validate(String token, String username) {
//        User dbUser = userRepository.findByUsername(username);
//        if(dbUser == null) {
//            throw new RuntimeException("User not found");
//        }
//        can be validation logic
//        if(!dbUser.getToken().equals(token)) {
//            throw new RuntimeException("Invalid token");
//        }

         return  jwtService.isTokenValid(token);

    }
}
