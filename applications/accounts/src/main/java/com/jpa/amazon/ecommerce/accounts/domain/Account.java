package com.jpa.amazon.ecommerce.accounts.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    @OneToMany(mappedBy = "account")
    @JsonManagedReference
    private List<Address> addresses;
}
