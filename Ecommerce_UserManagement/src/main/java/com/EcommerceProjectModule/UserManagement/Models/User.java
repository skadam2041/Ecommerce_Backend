package com.EcommerceProjectModule.UserManagement.Models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;


@Getter
@Setter
@ToString
@Entity(name = "users")
public class User extends BaseModel implements UserDetails {
    private String username;
    private String password;
    private String email;
    private String status;
    private String token;
    private Date created_at;
    private Date updated_at;

    @ManyToMany( fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles")
    @JsonManagedReference
    private List<Role> roles = new ArrayList<>();

    //override methods from UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));

        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
