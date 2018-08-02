package com.jpa.amazon.ecommerce.shipments.domain;

import lombok.Data;

@Data
public class OrderLineItems {

    private long id;
    private long product;
    private int quantity;
    private float price;
    private float totalPrice;
    private long shipment;
    private long account;
    private long order;

    public float getTotalPrice() {
        totalPrice = price * quantity;
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
