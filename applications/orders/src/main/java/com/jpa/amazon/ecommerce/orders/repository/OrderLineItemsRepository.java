package com.jpa.amazon.ecommerce.orders.repository;

import com.jpa.amazon.ecommerce.orders.domain.OrderLineItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineItemsRepository extends CrudRepository<OrderLineItems, Long> {
    List<OrderLineItems> findByOrderId(Long id);
    OrderLineItems getByIdAndOrderId(Long id, Long orderId);
    List<OrderLineItems> findByOrderIdAndShipment(Long orderId, Long shipment);
}
