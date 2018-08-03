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
        OrderLineItems[] orderLineItems = restTemplate.getForObject("//orders/orders/0/lines?shipmentId=" + ship.getId(), OrderLineItems[].class);
        return orderLineItems;
    }

    public OrderLineItems[] fallbackOrderLineItems(Shipment ship) {
        OrderLineItems[] orderLineItems = new OrderLineItems[0];
        return orderLineItems;
    }
}
