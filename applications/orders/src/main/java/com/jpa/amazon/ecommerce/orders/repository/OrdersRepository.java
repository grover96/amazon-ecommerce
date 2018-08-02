package com.jpa.amazon.ecommerce.orders.repository;
import com.jpa.amazon.ecommerce.orders.domain.OrderDetails;
import com.jpa.amazon.ecommerce.orders.domain.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {
    List<Orders> findByAccountOrderByOrderDateAsc(Long id);

    //@Query(value = "select new com.jpa.amazon.ecommerce.orders.domain.OrderDetails(orders.orderNumber AS orderNumber, orders.totalPrice AS ) FROM Orders orders WHERE orders.id = :id")
    //OrderDetails findByOrderDetails(Long id);
}
