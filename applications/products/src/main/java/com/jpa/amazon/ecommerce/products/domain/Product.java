package com.jpa.amazon.ecommerce.products.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private String image;
    private float price;

}
