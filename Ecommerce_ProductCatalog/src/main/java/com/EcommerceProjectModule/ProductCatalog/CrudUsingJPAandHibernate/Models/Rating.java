package com.EcommerceProjectModule.ProductCatalog.CrudUsingJPAandHibernate.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@ToString
@Entity(name = "jpa_rating")
public  class Rating {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double rate;
    private double count;
    @OneToOne(mappedBy = "rating",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private Product product;

}
