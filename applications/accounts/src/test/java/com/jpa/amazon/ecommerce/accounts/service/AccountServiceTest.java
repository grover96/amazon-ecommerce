package com.jpa.amazon.ecommerce.accounts.service;

import com.jpa.amazon.ecommerce.accounts.domain.Account;
import com.jpa.amazon.ecommerce.accounts.repository.AccountRepository;
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
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account = new Account();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        account.setId(1);
        account.setFirstName("Rohan");
        account.setLastName("Grover");
        account.setEmailAddress("rgrover@solstice.com");
    }

    @Test
    public void testCreateAccount() {
        when(accountRepository.save(any())).thenReturn(account);

        accountService.create(account);

        assertEquals(1, account.getId());
        assertEquals("Rohan", account.getFirstName());
        assertEquals("Grover", account.getLastName());
        assertEquals("rgrover@solstice.com", account.getEmailAddress());
    }

    @Test
    public void testUpdateAccount() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.ofNullable(account));

        account.setFirstName("Bill");
        account.setLastName("Cosby");
        accountService.update(Long.valueOf(1), account);

        assertEquals("Bill", account.getFirstName());
        assertNotEquals("Grover", account.getLastName());
        assertEquals("rgrover@solstice.com", account.getEmailAddress());
    }

    @Test
    public void testGetAccounts() {
        accountService.getAll();

        assertEquals(1, account.getId());
        assertNotEquals(2, account.getId());
    }

    @Test
    public void testDeleteAccount() {
        Account a = new Account();
        a.setId(1L);
        when(accountRepository.findById(anyLong())).thenReturn(Optional.ofNullable(a));

        accountService.delete(a.getId());

        assertNull(a.getFirstName());
        assertNull(a.getLastName());
        assertNull(a.getEmailAddress());
    }

    @Test
    public void testGetAccountById() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.ofNullable(account));

        accountService.getById(account.getId());

        assertNotNull(account);
    }
}
