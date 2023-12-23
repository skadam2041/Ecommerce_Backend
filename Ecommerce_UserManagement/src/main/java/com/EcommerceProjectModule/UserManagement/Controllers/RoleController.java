package com.EcommerceProjectModule.UserManagement.Controllers;

import com.EcommerceProjectModule.UserManagement.DTOs.RoleDTO;
import com.EcommerceProjectModule.UserManagement.Models.Role;
import com.EcommerceProjectModule.UserManagement.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {


    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO request) {
        try {
            Role role = roleService.createRole(request.getRoleName(), request.getDescription());
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleName(role.getRoleName());
            roleDTO.setDescription(role.getDescription());
            return new ResponseEntity<>(roleDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Role>> getRole() {
        List<Role> roles= roleService.getAllRole();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


}
