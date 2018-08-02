package com.jpa.amazon.ecommerce.orders.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class Shipment {
    private long id;
    private List<OrderLineItems> orderLineItems;
    private Date shippedDate;
    private Date deliveryDate;
}
