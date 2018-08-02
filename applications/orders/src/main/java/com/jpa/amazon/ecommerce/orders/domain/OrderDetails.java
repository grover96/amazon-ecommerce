package com.jpa.amazon.ecommerce.orders.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {

    private long orderNumber;
    private Address shippingAddress;
    private float totalPrice;
    @JsonIgnoreProperties({ "id", "shipment", "account", "price", "id" })
    private List<Shipment> shipment;
    @JsonIgnoreProperties({ "totalPrice", "shipment", "account", "price", "id", "product" })
    private List<OrderLineItems> lineItems;
}
