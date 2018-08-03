package com.jpa.amazon.ecommerce.orders.service;
import com.jpa.amazon.ecommerce.orders.domain.OrderLineItems;
import com.jpa.amazon.ecommerce.orders.domain.Orders;
import com.jpa.amazon.ecommerce.orders.repository.OrderLineItemsRepository;
import com.jpa.amazon.ecommerce.orders.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineItemsService {

    private OrderLineItemsRepository orderLineItemsRepository;
    private OrdersRepository ordersRepository;

    public OrderLineItemsService(OrderLineItemsRepository orderLineItemsRepository, OrdersRepository ordersRepository) {
        this.orderLineItemsRepository = orderLineItemsRepository;
        this.ordersRepository = ordersRepository;
    }

    public Iterable<OrderLineItems> getAllByOrderId(Long id){
        return orderLineItemsRepository.findByOrderId(id);
    }

    public OrderLineItems getById(Long id, Long orderId){
        return orderLineItemsRepository.getByIdAndOrderId(id, orderId);
    }

    public List<OrderLineItems> getByShipmentId(Long shipmentId) {
        return orderLineItemsRepository.getByShipment(shipmentId);
    }

    public OrderLineItems create(OrderLineItems orderLineItems, Long id) {
        Orders orders = ordersRepository.findById(id).get();
        orderLineItems.setOrder(orders);
        return orderLineItemsRepository.save(orderLineItems);
    }

    public OrderLineItems update(Long id, Long orderId, OrderLineItems orderLineItems){
        Orders orders = ordersRepository.findById(orderId).get();
        OrderLineItems orderLineItemsById = orderLineItemsRepository.findById(id).get();
        orderLineItems.setOrder(orders);
        orderLineItemsById.setPrice(orderLineItems.getPrice());
        orderLineItemsById.setQuantity(orderLineItems.getQuantity());
        orderLineItemsById.setTotalPrice(orderLineItems.getTotalPrice());
        return orderLineItemsRepository.save(orderLineItemsById);
    }

    public void delete(Long id, Long orderId){
        OrderLineItems orderLineItems = orderLineItemsRepository.getByIdAndOrderId(id, orderId);
        if(orderLineItems != null) {
            orderLineItemsRepository.deleteById(orderLineItems.getId());
        }
    }

}
