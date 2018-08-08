package com.jpa.amazon.ecommerce.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date shippedDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date deliveryDate;
    private long order;
}
