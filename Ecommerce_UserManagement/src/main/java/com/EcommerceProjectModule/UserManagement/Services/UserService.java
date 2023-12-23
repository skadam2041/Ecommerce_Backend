package com.EcommerceProjectModule.UserManagement.Services;

import com.EcommerceProjectModule.UserManagement.Models.Role;
import com.EcommerceProjectModule.UserManagement.Models.User;
import com.EcommerceProjectModule.UserManagement.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
@Transactional
public class UserService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    public User getUserDetailsById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return  user.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),user.isEnabled(),true,true,true,this.getAuthorities(user.getRoles()));
        return userDetails;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role  role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));

        }
        return authorities;
    }
}
