package com.jpa.amazon.ecommerce.orders.service;

import com.jpa.amazon.ecommerce.orders.domain.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HystrixService {

    private RestTemplate restTemplate;

    public HystrixService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "fallbackAddress")
    public Address getAddress(Orders orders) {
        Address address = restTemplate.getForObject("//accounts/accounts/" + orders.getAccount() + "/address/" + orders.getShippingAddress(), Address.class);
        return address;
    }

    public Address fallbackAddress(Orders orders){
        Address address = new Address();
        return address;
    }

    @HystrixCommand(fallbackMethod = "fallbackShipment")
    public Shipment getShipment(OrderLineItems orderLineItem){
        Shipment shipment = restTemplate.getForObject("//shipments/shipments/" + orderLineItem.getShipment(), Shipment.class);
        return shipment;
    }

    public Shipment fallbackShipment(OrderLineItems orderLineItem){
        Shipment shipment = new Shipment();
        return shipment;
    }

    @HystrixCommand(fallbackMethod = "fallbackProduct")
    public Product getProduct(OrderLineItems orderLineItem) {
        Product product = restTemplate.getForObject("//products/products/" + orderLineItem.getProduct(), Product.class);
        return product;
    }

    public Product fallbackProduct(OrderLineItems orderLineItems) {
        Product product = new Product();
        return product;
    }
}
