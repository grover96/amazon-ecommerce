package com.jpa.amazon.ecommerce.products.controller;

import com.jpa.amazon.ecommerce.products.domain.Product;
import com.jpa.amazon.ecommerce.products.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterable<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Product getById(@PathVariable("id") Long id){
        return productService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody Product update(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.update(id, product);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        productService.delete(id);
    }
}
