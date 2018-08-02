package com.jpa.amazon.ecommerce.shipments.domain;

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
    @GeneratedValue
    private long id;
    @Column(name = "account_id")
    private long account;
    @JsonIgnore
    @Column(name = "address_id")
    private long shippingAddress;
    @Transient
    @JsonInclude
    private long lineItems;
    private Date shippedDate;
    private Date deliveryDate;
    private long orderId;
    private long productId;

}
