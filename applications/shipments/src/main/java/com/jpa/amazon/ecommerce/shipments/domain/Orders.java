package com.jpa.amazon.ecommerce.shipments.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Orders {

    private long id;
    private long account;
    private long orderNumber;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date orderDate;
    private float totalPrice;

}
