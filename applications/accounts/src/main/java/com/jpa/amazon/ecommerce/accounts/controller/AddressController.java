package com.jpa.amazon.ecommerce.accounts.controller;

import com.jpa.amazon.ecommerce.accounts.domain.Address;
import com.jpa.amazon.ecommerce.accounts.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts/{id}/address")
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public Iterable<Address> getAllByAccountId(@PathVariable("id") Long id){
        return addressService.getAllByAccountId(id);
    }

    @GetMapping(value = "/{address_id}")
    public Address getById(@PathVariable("id") Long accountId, @PathVariable("address_id") Long id){
        return addressService.getById(id, accountId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Address create(@RequestBody Address address, @PathVariable("id") Long id) {
        return addressService.create(address, id);
    }

    @PutMapping(value = "/{address_id}")
    public @ResponseBody Address update(@PathVariable("address_id") Long id, @PathVariable("id") Long accountId, @RequestBody Address address){
        return addressService.update(id, accountId, address);
    }

    @DeleteMapping(value = "/{address_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long accountId, @PathVariable("address_id") Long id){
        addressService.delete(id, accountId);
    }
}
