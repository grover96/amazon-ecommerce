package com.jpa.amazon.ecommerce.orders.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "account_id")
    private long account;
    private long orderNumber;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date orderDate;
    @Column(name = "address_id")
    private long shippingAddress;
    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<OrderLineItems> lineItems;
    private float totalPrice;

}
