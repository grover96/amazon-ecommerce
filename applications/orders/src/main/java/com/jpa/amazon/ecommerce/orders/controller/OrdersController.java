package com.jpa.amazon.ecommerce.orders.controller;
import com.jpa.amazon.ecommerce.orders.domain.OrderDetails;
import com.jpa.amazon.ecommerce.orders.domain.Orders;
import com.jpa.amazon.ecommerce.orders.service.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public Iterable<Orders> getAll(){
        return ordersService.getAll();
    }

    //@GetMapping(value = "/{id}")
    //public Orders getById(@PathVariable("id") Long id){
    //    return ordersService.getById(id);
    //}

    @GetMapping(value = "/{id}")
    public OrderDetails getByOrderId(@PathVariable("id") Long id){
        return ordersService.getByOrderDetailsId(id);
   }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Orders create(@RequestBody Orders orders) {
        return ordersService.create(orders);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody Orders update(@PathVariable("id") Long id, @RequestBody Orders orders){
        return ordersService.update(id, orders);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        ordersService.delete(id);
    }

//    @GetMapping
//    public Iterable<Orders> findByAccountIdOrderByOrderDateAsc(@PathVariable("id") Long id){
//        return ordersService.findByAccountIdOrderByOrderDateAsc(id);
//    }

}
