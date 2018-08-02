package com.jpa.amazon.ecommerce.shipments.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShipmentDetails {

    private long accountId;
    private long orderNumber;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date shippedDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date deliveryDate;
    @JsonIgnoreProperties({"id", "price", "totalPrice", "shipment", "account"})
    private List<OrderLineItems> lineItems;
}
