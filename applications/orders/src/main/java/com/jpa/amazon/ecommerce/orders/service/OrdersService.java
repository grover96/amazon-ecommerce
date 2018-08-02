package com.jpa.amazon.ecommerce.orders.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.amazon.ecommerce.orders.domain.Address;
import com.jpa.amazon.ecommerce.orders.domain.OrderDetails;
import com.jpa.amazon.ecommerce.orders.domain.Orders;
import com.jpa.amazon.ecommerce.orders.domain.Shipment;
import com.jpa.amazon.ecommerce.orders.repository.OrdersRepository;
import org.apache.commons.collections.iterators.IteratorChain;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {

    private OrdersRepository ordersRepository;
    private RestTemplate restTemplate;

    public OrdersService(OrdersRepository ordersRepository, RestTemplate restTemplate) {
        this.ordersRepository = ordersRepository;
        this.restTemplate = restTemplate;
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

        Address address = restTemplate.getForObject("//accounts/accounts/" + orders.getAccount() + "/address/" + orders.getShippingAddress(), Address.class);
        orderDetails.setShippingAddress(address);

        ArrayList<Shipment> shipments = new ArrayList<>();

        orders.getLineItems().forEach(orderLineItem -> {
            Shipment shipment = restTemplate.getForObject("//shipments/shipments/" + orderLineItem.getShipment(), Shipment.class);
            shipments.add(shipment);
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

//    public List<Orders> getAllOrdersForAccount(long accountId) throws IOException {
//
//        ResponseEntity<String> response = restTemplate.getForEntity("//accounts/accounts/" + accountId, String.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode root = objectMapper.readTree(response.getBody());
//        JsonNode companyId = root.path("id");
//
//        System.out.println(companyId);
//        System.out.println(root);
//
//        return ordersRepository.findByAccountOrderByOrderDateAsc(companyId.asLong());
//    }

    public List<Orders> getAllOrdersForAccount(long accountId) throws IOException {
        return ordersRepository.findByAccountOrderByOrderDateAsc(accountId);
    }

}
