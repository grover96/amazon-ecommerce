package com.jpa.amazon.ecommerce.products.controller;

import com.jpa.amazon.ecommerce.products.service.ProductService;
import com.jpa.amazon.ecommerce.products.utilities.SecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@ContextConfiguration(classes = SecurityConfig.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;
    private String jsonContent;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        this.jsonContent = "{\"id\":\"1\",\"name\":\"Monopoly\",\"description\":\"board game\",\"image\":\"image\",\"price\":\"25\"}";
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testGetProductById() throws Exception {
        mockMvc.perform(get("/products/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAccount() throws Exception {
        mockMvc.perform(post("/products")
            .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateAccount() throws Exception {
        mockMvc.perform(put("/products/{id}", 2)
            .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testDeleteAccount() throws Exception {
        mockMvc.perform(delete("/products/{id}", 1)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
