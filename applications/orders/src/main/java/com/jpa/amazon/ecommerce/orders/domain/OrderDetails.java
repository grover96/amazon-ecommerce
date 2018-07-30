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
    private long shippingAddressId;
    private float totalPrice;
    private long shipmentId;
    @JsonIgnoreProperties({ "totalPrice", "shipment", "account", "price", "id" })
    private List<OrderLineItems> orderLineItems;
}
