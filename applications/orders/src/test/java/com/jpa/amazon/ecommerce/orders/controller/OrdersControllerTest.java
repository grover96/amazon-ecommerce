package com.jpa.amazon.ecommerce.orders.controller;

import com.jpa.amazon.ecommerce.orders.service.OrdersService;
import com.jpa.amazon.ecommerce.orders.utilities.SecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrdersController.class)
@ContextConfiguration(classes = SecurityConfig.class)
public class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OrdersService ordersService;

    @InjectMocks
    private OrdersController ordersController;
    private String jsonContent;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(ordersController).build();
        this.jsonContent = "{\"id\":\"1\",\"account\":\"1\",\"orderNumber\":\"2922\",\"orderDate\":\"2017-16-12\",\"shippingAddress\":\"1\",\"totalPrice\":\"50\"}";
    }

//    @Test
//    public void testGetAllOrders() throws Exception {
//        mockMvc.perform(get("/orders"))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }

    @Test
    public void testGetOrderById() throws Exception {
        mockMvc.perform(get("/orders/{id}", 1))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testCreateOrder() throws Exception {
        mockMvc.perform(post("/orders")
            .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testUpdateOrder() throws Exception {
        mockMvc.perform(put("/orders/{id}", 1)
            .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testDeleteOrder() throws Exception {
        mockMvc.perform(delete("/orders/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
