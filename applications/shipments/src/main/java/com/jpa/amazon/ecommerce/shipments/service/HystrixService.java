package com.jpa.amazon.ecommerce.shipments.service;

import com.jpa.amazon.ecommerce.shipments.domain.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HystrixService {

    private RestTemplate restTemplate;

    public HystrixService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "fallbackProducts")
    public Product getProducts(OrderLineItems orderLineItems) {
        Product product = restTemplate.getForObject("//products/products/" + orderLineItems.getProduct(), Product.class);
        return product;
    }

    public Product fallbackProducts(OrderLineItems orderLineItems) {
        Product product = new Product();
        return product;
    }

    @HystrixCommand(fallbackMethod = "fallbackOrderLineItems")
    public OrderLineItems[] getOrderLineItems(Shipment ship) {
        OrderLineItems[] orderLineItems = restTemplate.getForObject("//orders/orders/" + ship.getOrder() + "/lines/shipments?shipmentId=" + ship.getId(), OrderLineItems[].class);
        return orderLineItems;
    }

    public OrderLineItems[] fallbackOrderLineItems(Shipment ship) {
        OrderLineItems[] orderLineItems = new OrderLineItems[0];
        return orderLineItems;
    }

    @HystrixCommand(fallbackMethod = "fallbackOrders")
    public Orders getOrders(Shipment shipment){
        Orders orders = restTemplate.getForObject("//orders/orders/" + shipment.getOrder(), Orders.class);
        return orders;
    }

    public Orders fallbackOrders(Shipment shipment) {
        Orders orders = new Orders();
        return orders;
    }
}
