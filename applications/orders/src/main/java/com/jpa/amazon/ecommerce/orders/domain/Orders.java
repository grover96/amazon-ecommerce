package com.jpa.amazon.ecommerce.orders.domain;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "account_id")
    private long account;
    private long orderNumber;
    private Date orderDate;
    @Column(name = "address_id")
    private long shippingAddress;
    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<OrderLineItems> orderLineItems;
    private float totalPrice;

}
