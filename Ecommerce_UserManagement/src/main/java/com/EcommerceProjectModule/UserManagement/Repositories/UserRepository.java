package com.EcommerceProjectModule.UserManagement.Repositories;

import com.EcommerceProjectModule.UserManagement.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);


}
