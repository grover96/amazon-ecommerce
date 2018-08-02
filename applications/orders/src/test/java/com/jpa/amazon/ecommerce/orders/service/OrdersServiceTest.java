package com.jpa.amazon.ecommerce.orders.service;

import com.jpa.amazon.ecommerce.orders.domain.Address;
import com.jpa.amazon.ecommerce.orders.domain.OrderDetails;
import com.jpa.amazon.ecommerce.orders.domain.Orders;
import com.jpa.amazon.ecommerce.orders.repository.OrdersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OrdersServiceTest {

    @Mock
    private OrdersRepository ordersRepository;

    @InjectMocks
    private OrdersService ordersService;

    private Orders orders = new Orders();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        orders.setId(1);
        orders.setOrderDate(Date.valueOf("2018-09-02"));
        orders.setTotalPrice(34.5f);
    }

    @Test
    public void testCreateOrder() {
        when(ordersRepository.save(any())).thenReturn(orders);

        ordersService.create(orders);

        assertEquals(1, orders.getId());
        assertEquals(Date.valueOf("2018-09-02"), orders.getOrderDate());
    }

    @Test
    public void testUpdateOrder() {
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.ofNullable(orders));

        orders.setOrderDate(Date.valueOf("2018-09-03"));
        orders.setOrderNumber(1);
        ordersService.update(Long.valueOf(1), orders);

        assertEquals(Date.valueOf("2018-09-03"), orders.getOrderDate());
        assertNotEquals(Date.valueOf("2018-09-02"), orders.getOrderDate());
        assertEquals(1, orders.getOrderNumber());
    }

    @Test
    public void testGetOrders() {
        ordersService.getAll();

        assertEquals(1, orders.getId());
        assertNotEquals(2, orders.getId());
    }

    @Test
    public void testDeleteOrder() {
        Orders o = new Orders();
        o.setId(1L);
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.ofNullable(o));

        ordersService.delete(o.getId());

        assertNull(o.getOrderDate());
        assertNull(o.getLineItems());
    }

    @Test
    public void testGetOrderById() {
        List<Orders> order = new ArrayList<>();
        Orders o = new Orders();
        o.setOrderNumber(3);
        o.setId(2);
        o.setShippingAddress(3);

        order.add(o);
        order.add(orders);

        when(ordersRepository.findById(anyLong())).thenReturn(Optional.of(o));
        ordersService.getById(orders.getId());

        Address address = new Address();
        address.setApt("34f");

        when(ordersRepository.findByAccountOrderByOrderDateAsc(anyLong())).thenReturn(order);

        ordersService.getByOrderDetailsId(order.get(0).getId());

        assertNotNull(orders);
    }

    @Test
    public void testAllOrdersForAccount() throws IOException {
        ordersService.getAllOrdersForAccount(orders.getId());

        assertEquals(1, orders.getId());
        assertNotEquals(2, orders.getId());
    }

    @Test
    public void testConstructors() {

        RestTemplate restTemplate = new RestTemplate();
        OrdersRepository ordersRepository = null;

        OrdersService service = new OrdersService(ordersRepository, restTemplate);

        assertNotNull(service);

    }
}
