package com.jpa.amazon.ecommerce.shipments.repository;

import com.jpa.amazon.ecommerce.shipments.domain.Shipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends CrudRepository<Shipment, Long> {
}
