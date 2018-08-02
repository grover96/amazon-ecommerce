package com.jpa.amazon.ecommerce.orders.domain;

import lombok.Data;

@Data
public class Product {

    private String name;
    private String description;
    private String image;
    private float price;
}
