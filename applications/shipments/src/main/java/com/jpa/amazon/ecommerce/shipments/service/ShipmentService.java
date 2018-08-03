package com.jpa.amazon.ecommerce.shipments.service;

import com.jpa.amazon.ecommerce.shipments.domain.*;
import com.jpa.amazon.ecommerce.shipments.repository.ShipmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ShipmentService {

    private ShipmentRepository shipmentRepository;
    private HystrixService hystrixService;

    public ShipmentService(ShipmentRepository shipmentRepository, HystrixService hystrixService) {
        this.shipmentRepository = shipmentRepository;
        this.hystrixService = hystrixService;
    }

    public Iterable<Shipment> getAll() {
        return shipmentRepository.findAll();
    }

    public Shipment getById(Long id) {
        return shipmentRepository.findById(id).get();
    }

    public List<ShipmentDetails> getAllOrdersForAccount(Long accountId) throws IOException {
        List<ShipmentDetails> shipmentDetailsList = new ArrayList<>();

        List<Shipment> shipments = shipmentRepository.findByAccountOrderByDeliveryDateAsc(accountId);
        shipments.forEach(ship -> {
            ShipmentDetails shipmentDetails = new ShipmentDetails();
            shipmentDetails.setId(ship.getId());
            shipmentDetails.setAccountId(ship.getAccount());
            shipmentDetails.setDeliveryDate(ship.getDeliveryDate());
            shipmentDetails.setShippedDate(ship.getShippedDate());

            OrderLineItems[] orderLineItems = hystrixService.getOrderLineItems(ship);
            List<OrderLineItems> lineItems = Arrays.asList(orderLineItems);

            Arrays.asList(orderLineItems).forEach(orderLineItems1 -> {
                Product product = hystrixService.getProducts(orderLineItems1);
                orderLineItems1.setProductName(product.getName());
            });

            shipmentDetails.setLineItems(lineItems);
            //shipmentDetails.setOrderNumber();

            shipmentDetailsList.add(shipmentDetails);
        });

        return shipmentDetailsList;
    }

    public Shipment create(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public Shipment update(Long id, Shipment shipment) {
        Shipment shipmentById = shipmentRepository.findById(id).get();
        shipmentById.setDeliveryDate(shipment.getDeliveryDate());
        shipmentById.setShippedDate(shipment.getShippedDate());
        return shipmentRepository.save(shipmentById);
    }

    public void delete(Long id) {
        shipmentRepository.deleteById(id);
    }
}
