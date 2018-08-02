package com.jpa.amazon.ecommerce.accounts.service;

import com.jpa.amazon.ecommerce.accounts.domain.Account;
import com.jpa.amazon.ecommerce.accounts.domain.Address;
import com.jpa.amazon.ecommerce.accounts.repository.AccountRepository;
import com.jpa.amazon.ecommerce.accounts.repository.AddressRepository;
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
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AddressService addressService;

    private Address address = new Address();
    private Account account = new Account();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        account.setId(1);
        account.setFirstName("Michael");
        account.setLastName("Jordan");

        address.setId(1);
        address.setStreet("768 Greenwood Ave.");
        address.setCountry("USA");
    }

    @Test
    public void testCreateAddress() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.ofNullable(account));
        when(addressRepository.save(any())).thenReturn(address);

        addressService.create(address, account.getId());

        assertEquals(1, address.getId());
        assertEquals("USA", address.getCountry());
    }

    @Test
    public void testUpdateAddress() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.ofNullable(account));
        when(addressRepository.findById(anyLong())).thenReturn(Optional.ofNullable(address));

        address.setId(2);
        address.setStreet("484 Fairfield Dr.");
        addressService.update(address.getId(), account.getId(), address);

        assertEquals(2, address.getId());
        assertNotEquals(1, address.getId());
        assertEquals("484 Fairfield Dr.", address.getStreet());
    }

    @Test
    public void testGetAddresses() {
        addressService.getAllByAccountId(account.getId());

        assertEquals(1, address.getId());
        assertNotEquals(2, address.getId());
    }

    @Test
    public void testDeleteAddress() {
        Account acc = new Account();
        acc.setId(1L);
        Address add = new Address();
        add.setId(2L);
        when(accountRepository.findById(anyLong())).thenReturn(Optional.ofNullable(acc));
        when(addressRepository.findById(anyLong())).thenReturn(Optional.ofNullable(add));

        addressService.delete(acc.getId(), add.getId());

        assertNull(add.getStreet());
    }

    @Test
    public void testGetAddressById() {

        when(accountRepository.findById(anyLong())).thenReturn(Optional.ofNullable(account));
        when(addressRepository.findById(anyLong())).thenReturn(Optional.ofNullable(address));

        addressService.getById(address.getId(), account.getId());

        assertNotNull(address);
    }

}
