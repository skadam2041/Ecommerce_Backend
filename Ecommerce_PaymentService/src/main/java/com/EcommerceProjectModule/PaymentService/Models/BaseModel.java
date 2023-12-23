package com.EcommerceProjectModule.PaymentService.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseModel {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
}
