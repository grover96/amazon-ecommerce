package com.jpa.amazon.ecommerce.accounts.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    @OneToMany(mappedBy = "account")
    @JsonManagedReference
    private List<Address> addresses;
}
