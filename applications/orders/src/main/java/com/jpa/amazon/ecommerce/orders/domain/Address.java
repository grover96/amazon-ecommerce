package com.jpa.amazon.ecommerce.orders.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Address {

    private String street;
    private String apt;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
