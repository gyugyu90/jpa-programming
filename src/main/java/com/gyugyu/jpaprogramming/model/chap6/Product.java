package com.gyugyu.jpaprogramming.model.chap6;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Id @Column(name = "PRODUCT_ID")
    private String id;

    private String name;

    
}
