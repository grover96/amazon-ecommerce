package com.jpa.amazon.ecommerce.shipments.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "account_id")
    private long account;
    @Column(name = "order_id")
    private long order;
    @JsonIgnore
    @Column(name = "address_id")
    private long shippingAddress;
    @Transient
    @JsonInclude
    private long lineItems;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date shippedDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date deliveryDate;

}
