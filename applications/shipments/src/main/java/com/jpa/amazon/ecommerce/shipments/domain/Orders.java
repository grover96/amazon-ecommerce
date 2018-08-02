package com.jpa.amazon.ecommerce.shipments.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Orders {

    private long id;
    private long account;
    private long orderNumber;
    private Date orderDate;
    private float totalPrice;

}
