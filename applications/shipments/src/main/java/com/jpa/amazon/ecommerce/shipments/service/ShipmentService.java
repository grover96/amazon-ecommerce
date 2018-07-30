package com.jpa.amazon.ecommerce.shipments.service;

import com.jpa.amazon.ecommerce.shipments.domain.Shipment;
import com.jpa.amazon.ecommerce.shipments.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {

    private ShipmentRepository shipmentRepository;

    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public Iterable<Shipment> getAll(){
        return shipmentRepository.findAll();
    }

    public Shipment getById(Long id){
        return shipmentRepository.findById(id).get();
    }

    public Shipment create(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public Shipment update(Long id, Shipment shipment){
        Shipment shipmentById = shipmentRepository.findById(id).get();
        shipmentById.setDeliveryDate(shipment.getDeliveryDate());
        shipmentById.setShippedDate(shipment.getShippedDate());
        return shipmentRepository.save(shipmentById);
    }

    public void delete(Long id){
        shipmentRepository.deleteById(id);
    }
}
