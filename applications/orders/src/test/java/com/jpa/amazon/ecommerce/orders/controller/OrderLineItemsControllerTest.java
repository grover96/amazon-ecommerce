package com.jpa.amazon.ecommerce.orders.controller;

import com.jpa.amazon.ecommerce.orders.service.OrderLineItemsService;
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
@WebMvcTest(OrderLineItemsController.class)
@ContextConfiguration(classes = SecurityConfig.class)
public class OrderLineItemsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OrderLineItemsService orderLineItemsService;

    @InjectMocks
    private OrderLineItemsController orderLineItemsController;
    private String jsonContent;
    private Integer orderId;
    private Integer orderLineItemId;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(orderLineItemsController).build();
        this.jsonContent = "{\"id\":\"1\",\"product\":\"1\",\"quantity\":\"2\",\"price\":\"10\",\"totalPrice\":\"20\",\"shipment\":\"1\",\"account\":\"1\"}";
        this.orderId = 1;
        this.orderLineItemId = 1;
    }

    @Test
    public void testGetAllOrderLineItems() throws Exception {
        mockMvc.perform(get("/orders/{id}/lines", orderId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetOrderLineItemById() throws Exception {
        mockMvc.perform(get("/orders/{id}/lines/{orderLineItems_id}", orderId, orderLineItemId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testCreateOrderLineItem() throws Exception {
        mockMvc.perform(post("/orders/{id}/lines", orderId)
            .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testUpdateOrderLineItem() throws Exception {
        mockMvc.perform(put("/orders/{id}/lines/{orderLineItems_id}", orderId, orderLineItemId)
                .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testDeleteOrderLineItem() throws Exception {
        mockMvc.perform(delete("/orders/{id}/lines/{orderLineItems_id}", orderId, orderLineItemId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
