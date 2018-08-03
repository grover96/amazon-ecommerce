package com.jpa.amazon.ecommerce.shipments.service;

import com.jpa.amazon.ecommerce.shipments.domain.OrderLineItems;
import com.jpa.amazon.ecommerce.shipments.domain.Orders;
import com.jpa.amazon.ecommerce.shipments.domain.Product;
import com.jpa.amazon.ecommerce.shipments.domain.Shipment;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HystrixService {

    private RestTemplate restTemplate;

    public HystrixService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "fallbackOrders")
    public Orders getOrders(Shipment ship) {
        Orders orders = restTemplate.getForObject("//orders/orders/" + ship.getOrderId(), Orders.class);
        return orders;
    }

    public Orders fallbackOrders(Shipment ship) {
        Orders orders = new Orders();
        return orders;
    }

    @HystrixCommand(fallbackMethod = "fallbackProducts")
    public Product getProducts(Shipment ship) {
        Product product = restTemplate.getForObject("//products/products/" + ship.getProductId(), Product.class);
        return product;
    }

    public Product fallbackProducts(Shipment ship) {
        Product product = new Product();
        return product;
    }

    @HystrixCommand(fallbackMethod = "fallbackOrderLineItems")
    public OrderLineItems[] getOrderLineItems(Shipment ship) {
        OrderLineItems[] orderLineItems = restTemplate.getForObject("//orders/orders/" + ship.getOrderId() + "/lines", OrderLineItems[].class);
        return orderLineItems;
    }

    public OrderLineItems[] fallbackOrderLineItems(Shipment ship) {
        OrderLineItems[] orderLineItems = new OrderLineItems[0];
        return orderLineItems;
    }
}
