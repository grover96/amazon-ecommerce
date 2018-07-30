package com.jpa.amazon.ecommerce.accounts.controller;

import com.jpa.amazon.ecommerce.accounts.service.AccountService;
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
@WebMvcTest(AccountController.class)
@ContextConfiguration(classes = SecurityConfig.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;
    private String jsonContent;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(this.accountController).build();
        this.jsonContent = "{\"id\":\"1\",\"firstName\":\"Rohan\",\"lastName\":\"Grover\",\"emailAddress\":\"rgrover@solstice.com\"}";
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        mvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetShipmentById() throws Exception {
        mvc.perform(get("/accounts/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAccount() throws Exception {
        mvc.perform(post("/accounts")
            .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testUpdateAccount() throws Exception {
        mvc.perform(put("/accounts/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testDeleteAccount() throws Exception {
        mvc.perform(delete("/accounts/{id}",1)
            .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
