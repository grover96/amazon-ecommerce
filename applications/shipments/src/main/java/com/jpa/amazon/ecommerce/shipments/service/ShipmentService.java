package com.jpa.amazon.ecommerce.shipments.service;

import com.jpa.amazon.ecommerce.shipments.domain.OrderLineItems;
import com.jpa.amazon.ecommerce.shipments.domain.Orders;
import com.jpa.amazon.ecommerce.shipments.domain.Shipment;
import com.jpa.amazon.ecommerce.shipments.domain.ShipmentDetails;
import com.jpa.amazon.ecommerce.shipments.repository.ShipmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentService {

    private ShipmentRepository shipmentRepository;
    private RestTemplate restTemplate;

    public ShipmentService(ShipmentRepository shipmentRepository, RestTemplate restTemplate) {
        this.shipmentRepository = shipmentRepository;
        this.restTemplate = restTemplate;
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
            shipmentDetails.setAccountId(ship.getAccount());
            shipmentDetails.setDeliveryDate(ship.getDeliveryDate());
            shipmentDetails.setShippedDate(ship.getShippedDate());

            Orders orders = restTemplate.getForObject("//orders/orders/" + ship.getOrderId(), Orders.class);

            List<OrderLineItems> lineItems = restTemplate.getForObject("//orders/orders/" + ship.getOrderId() + "/lines", List.class);
            shipmentDetails.setLineItems(lineItems);

            shipmentDetails.setOrderNumber(orders.getOrderNumber());

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
