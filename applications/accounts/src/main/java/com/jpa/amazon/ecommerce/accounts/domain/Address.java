package com.jpa.amazon.ecommerce.accounts.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String apt;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    @ManyToOne
    @JsonBackReference
    private Account account;
}
