package com.jpa.amazon.ecommerce.shipments.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Shipment {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "account_id")
    private long account;
    @Column(name = "address_id")
    private long shippingAddress;
    @Column(name = "orderLineItem_id")
    private long orderLineItems;
    private Date shippedDate;
    private Date deliveryDate;

}
