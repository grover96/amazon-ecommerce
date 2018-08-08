package com.jpa.amazon.ecommerce.orders.controller;

import com.jpa.amazon.ecommerce.orders.domain.OrderLineItems;
import com.jpa.amazon.ecommerce.orders.service.OrderLineItemsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/{id}/lines")
public class OrderLineItemsController {

    private
    OrderLineItemsService orderLineItemsService;

    public OrderLineItemsController(OrderLineItemsService orderLineItemsService) {
        this.orderLineItemsService = orderLineItemsService;
    }

    @GetMapping
    public Iterable<OrderLineItems> getAllByOrderId(@PathVariable("id") Long id) {
        return orderLineItemsService.getAllByOrderId(id);
    }

    @GetMapping(value = "/{orderLineItems_id}")
    public OrderLineItems getById(@PathVariable("id") Long orderId, @PathVariable("orderLineItems_id") Long id){
        return orderLineItemsService.getById(id, orderId);
    }

    @GetMapping(value = "/shipments")
    public List<OrderLineItems> findByShipmentId(@PathVariable("id") Long orderId, @RequestParam("shipmentId") Long shipment){
        return orderLineItemsService.findByOrderIdAndShipment(orderId, shipment);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    OrderLineItems create(@RequestBody OrderLineItems orderLineItems, @PathVariable("id") Long id) {
        return orderLineItemsService.create(orderLineItems, id);
    }

    @PutMapping(value = "/{orderLineItems_id}")
    public @ResponseBody OrderLineItems update(@PathVariable("orderLineItems_id") Long id, @PathVariable("id") Long orderId, @RequestBody OrderLineItems orderLineItems){
        return orderLineItemsService.update(id, orderId, orderLineItems);
    }

    @DeleteMapping(value = "/{orderLineItems_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long orderId, @PathVariable("orderLineItems_id") Long id){
        orderLineItemsService.delete(id, orderId);
}
}
