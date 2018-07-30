package com.jpa.amazon.ecommerce.orders.service;

import com.jpa.amazon.ecommerce.orders.domain.OrderDetails;
import com.jpa.amazon.ecommerce.orders.domain.Orders;
import com.jpa.amazon.ecommerce.orders.repository.OrdersRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    private OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Iterable<Orders> getAll(){
        return ordersRepository.findAll();
    }

//    public Orders getById(Long id){
//        return ordersRepository.findById(id).get();
//    }

    public OrderDetails getByOrderDetailsId(Long id) {
        OrderDetails orderDetails = new OrderDetails();
        Orders orders = ordersRepository.findById(id).get();
        orderDetails.setOrderNumber(orders.getOrderNumber());
        orderDetails.setTotalPrice(orders.getTotalPrice());
        orderDetails.setShippingAddressId(orders.getShippingAddress());
        orderDetails.setOrderLineItems(orders.getOrderLineItems());
        return orderDetails;
    }

    public Orders create(Orders orders) {
        return ordersRepository.save(orders);
    }

    public Orders update(Long id, Orders orders){
        Orders ordersById = ordersRepository.findById(id).get();
        ordersById.setOrderNumber(orders.getOrderNumber());
        ordersById.setOrderDate(orders.getOrderDate());
        ordersById.setTotalPrice(orders.getTotalPrice());
        return ordersRepository.save(ordersById);
    }

    public void delete(Long id){
        ordersRepository.deleteById(id);
    }

}
