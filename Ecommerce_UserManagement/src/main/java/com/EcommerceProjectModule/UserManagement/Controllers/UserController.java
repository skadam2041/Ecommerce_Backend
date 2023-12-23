package com.EcommerceProjectModule.UserManagement.Controllers;


import com.EcommerceProjectModule.UserManagement.DTOs.ResponceToUser;
import com.EcommerceProjectModule.UserManagement.Models.User;
import com.EcommerceProjectModule.UserManagement.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponceToUser> getUserDetailsById(@PathVariable("id") Long userId) {
        System.out.println("User id is " + userId);
        User user = userService.getUserDetailsById(userId);
        ResponceToUser responceToUser = new ResponceToUser();
        responceToUser.setMessage("User details fetched successfully");
        responceToUser.setStatus("Active");
        responceToUser.setUsername(user.getUsername());
        responceToUser.setEmail(user.getEmail());
        responceToUser.setRoles(user.getRoles());
        return new ResponseEntity<>(responceToUser, HttpStatus.OK);
    }


//    @PostMapping("/{id}/roles")
//    public ResponseEntity<User> setUserRoles(@PathVariable("id") Long userId, @RequestBody SetUserRolesRequestDto request) {
//
//        User user = userService.setUserRoles(userId, request.getRoleIds());
//
//        return new ResponseEntity<>(userDto, HttpStatus.OK);
//    }

}
