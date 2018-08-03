package com.jpa.amazon.ecommerce.orders.service;

import com.jpa.amazon.ecommerce.orders.domain.*;
import com.jpa.amazon.ecommerce.orders.repository.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {

    private OrdersRepository ordersRepository;
    private RestTemplate restTemplate;
    private HystrixService hystrixService;

    public OrdersService(OrdersRepository ordersRepository, RestTemplate restTemplate, HystrixService hystrixService) {
        this.ordersRepository = ordersRepository;
        this.restTemplate = restTemplate;
        this.hystrixService = hystrixService;
    }

    public Iterable<Orders> getAll() {
        return ordersRepository.findAll();
    }

    public Orders getById(Long id) {
        return ordersRepository.findById(id).get();
    }

    public OrderDetails getByOrderDetailsId(Long id) {
        OrderDetails orderDetails = new OrderDetails();
        Orders orders = ordersRepository.findById(id).get();

        orderDetails.setOrderNumber(orders.getOrderNumber());
        orderDetails.setTotalPrice(orders.getTotalPrice());

        Address address = hystrixService.getAddress(orders);
        orderDetails.setShippingAddress(address);

        List<Shipment> shipments = new ArrayList<>();

        orders.getLineItems().forEach(orderLineItem -> {
            Shipment shipment = hystrixService.getShipment(orderLineItem);
            shipment.setOrderLineItems(orders.getLineItems());
            shipments.add(shipment);

            Product product = hystrixService.getProduct(orderLineItem);
            orderLineItem.setProductName(product.getName());
        });

        orderDetails.setShipment(shipments);
        orderDetails.setLineItems(orders.getLineItems());

        return orderDetails;
    }

    public Orders create(Orders orders) {
        return ordersRepository.save(orders);
    }

    public Orders update(Long id, Orders orders) {
        Orders ordersById = ordersRepository.findById(id).get();
        ordersById.setOrderNumber(orders.getOrderNumber());
        ordersById.setOrderDate(orders.getOrderDate());
        ordersById.setTotalPrice(orders.getTotalPrice());
        return ordersRepository.save(ordersById);
    }

    public void delete(Long id) {
        ordersRepository.deleteById(id);
    }

    public List<Orders> getAllOrdersForAccount(long accountId) throws IOException {
        return ordersRepository.findByAccountOrderByOrderDateAsc(accountId);
    }

}
