package com.jpa.amazon.ecommerce.shipments.controller;

import com.jpa.amazon.ecommerce.shipments.domain.Shipment;
import com.jpa.amazon.ecommerce.shipments.domain.ShipmentDetails;
import com.jpa.amazon.ecommerce.shipments.service.ShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("/")
    public Iterable<Shipment> getAll(){
        return shipmentService.getAll();
    }

    @GetMapping
    public List<ShipmentDetails> getAllOrdersForAccount(@RequestParam("accountId") long accountId) throws IOException {
        return shipmentService.getAllOrdersForAccount(accountId);
    }

    @GetMapping(value = "/{id}")
    public Shipment getById(@PathVariable("id") Long id){
        return shipmentService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Shipment create(@RequestBody Shipment shipment) {
        return shipmentService.create(shipment);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody Shipment update(@PathVariable("id") Long id, @RequestBody Shipment shipment){
        return shipmentService.update(id, shipment);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        shipmentService.delete(id);
    }
}
