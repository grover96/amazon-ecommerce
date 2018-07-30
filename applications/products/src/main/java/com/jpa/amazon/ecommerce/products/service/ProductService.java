package com.jpa.amazon.ecommerce.products.service;

import com.jpa.amazon.ecommerce.products.domain.Product;
import com.jpa.amazon.ecommerce.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getById(Long id){
        return productRepository.findById(id).get();
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product product){
        Product productById = productRepository.findById(id).get();
        productById.setDescription(product.getDescription());
        productById.setImage(product.getImage());
        productById.setName(product.getName());
        productById.setPrice(product.getPrice());
        return productRepository.save(productById);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

}
