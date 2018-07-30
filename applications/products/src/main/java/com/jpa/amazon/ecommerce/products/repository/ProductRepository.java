package com.jpa.amazon.ecommerce.products.repository;

import com.jpa.amazon.ecommerce.products.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
