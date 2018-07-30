package com.jpa.amazon.ecommerce.accounts.controller;

import com.jpa.amazon.ecommerce.accounts.service.AddressService;
import com.jpa.amazon.ecommerce.accounts.utilities.SecurityConfig;
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
@WebMvcTest(AddressController.class)
@ContextConfiguration(classes = SecurityConfig.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;
    private String jsonContent;
    private Integer accountId;
    private Integer addressId;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
        this.jsonContent = "{\"id\":\"1\",\"street\":\"574 Wabash Ave.\",\"apt\":\"3D\",\"city\":\"Chicago\",\"state\":\"IL\",\"zipCode\":\"60660\",\"state\":\"IL\",\"country\":\"USA\"}";
        this.accountId = 1;
        this.addressId = 2;
    }

    @Test
    public void testGetAllAddresses() throws Exception {
        mockMvc.perform(get("/accounts/{id}/address", accountId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetAddressById() throws Exception {
        mockMvc.perform(get("/accounts/{id}/address/{address_id}", accountId, addressId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testCreateAddress() throws Exception {
        mockMvc.perform(post("/accounts/{id}/address", accountId)
                .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testUpdateAddress() throws Exception {
        mockMvc.perform(put("/accounts/{id}/address/{address_id}", accountId, addressId)
                .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testDeleteAccount() throws Exception {
        mockMvc.perform(delete("/accounts/{id}/address/{address_id}", accountId, addressId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
