package com.jpa.amazon.ecommerce.shipments.controller;

import com.jpa.amazon.ecommerce.shipments.service.ShipmentService;
import com.jpa.amazon.ecommerce.shipments.utilities.SecurityConfig;
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
@WebMvcTest(ShipmentController.class)
@ContextConfiguration(classes = SecurityConfig.class)
public class ShipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ShipmentService shipmentService;

    @InjectMocks
    private ShipmentController shipmentController;
    private String jsonContent;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(shipmentController).build();
        this.jsonContent = "{\"id\":\"1\",\"account\":\"1\",\"shippingAddress\":\"1\",\"orderLineItems\":\"1\",\"shippedDate\":\"2017-12-14T06:00:00.000+0000\",\"deliveryDate\":\"2017-12-14T06:00:00.000+0000\"}";
    }

    @Test
    public void testGetAllShipments() throws  Exception {
        mockMvc.perform(get("/shipments"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetShipmentById() throws Exception {
        mockMvc.perform(get("/shipments/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateShipment() throws  Exception {
        mockMvc.perform(post("/shipments")
            .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testUpdateShipment() throws Exception {
        mockMvc.perform(put("/shipments/{id}", 2)
            .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testDeleteShipment() throws  Exception {
        mockMvc.perform(delete("/shipments/{id}", 1)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
