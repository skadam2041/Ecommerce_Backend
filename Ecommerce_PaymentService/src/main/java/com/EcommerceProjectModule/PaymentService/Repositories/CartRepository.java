package com.EcommerceProjectModule.PaymentService.Repositories;

import com.EcommerceProjectModule.PaymentService.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
