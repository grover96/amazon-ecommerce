package com.jpa.amazon.ecommerce.orders.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "product_id")
    private long product;
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String productName;
    private int quantity;
    private float price;
    private float totalPrice;
    @Column(name = "shipment_id")
    private long shipment;
    @ManyToOne
    @JsonBackReference
    private Orders order;
    @Column(name = "account_id")
    private long account;

    public float getTotalPrice() {
        totalPrice = price * quantity;
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

}
