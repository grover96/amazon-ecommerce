package com.jpa.amazon.ecommerce.products.service;

import com.jpa.amazon.ecommerce.products.domain.Product;
import com.jpa.amazon.ecommerce.products.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = SecurityConfig.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product = new Product();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        product.setId(1);
        product.setName("Monopoly");
        product.setPrice(145f);
        product.setImage("");
        product.setDescription("board game");
    }

    @Test
    public void testCreateProduct() {
        when(productRepository.save(any())).thenReturn(product);

        productService.create(product);

        assertEquals(1, product.getId());
        assertEquals("Monopoly", product.getName());
    }

    @Test
    public void testUpdateProduct() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product));

        product.setName("Jenga");
        product.setPrice(45f);
        productService.update(Long.valueOf(1), product);

        assertEquals("Jenga", product.getName());
        assertNotEquals("45f", product.getPrice());
    }

    @Test
    public void testGetProducts() {
        productService.getAll();

        assertEquals(1, product.getId());
        assertNotEquals(2, product.getId());
    }

    @Test
    public void testDeleteProducts() {
        Product p = new Product();
        p.setId(1L);
        when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(p));

        productService.delete(p.getId());

        assertNull(p.getName());
        assertNull(p.getDescription());
    }

    @Test
    public void testGetProductById() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product));

        productService.getById(product.getId());

        assertNotNull(product);
    }
}
