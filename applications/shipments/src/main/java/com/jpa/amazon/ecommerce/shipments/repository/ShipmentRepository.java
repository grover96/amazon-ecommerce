package com.jpa.amazon.ecommerce.shipments.repository;

import com.jpa.amazon.ecommerce.shipments.domain.Shipment;
import com.jpa.amazon.ecommerce.shipments.domain.ShipmentDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends CrudRepository<Shipment, Long> {
    List<Shipment> findByAccountOrderByDeliveryDateAsc(Long accountId);
}
