package com.jpa.amazon.ecommerce.orders.service;

import com.jpa.amazon.ecommerce.orders.domain.Address;
import com.jpa.amazon.ecommerce.orders.domain.OrderLineItems;
import com.jpa.amazon.ecommerce.orders.domain.Orders;
import com.jpa.amazon.ecommerce.orders.repository.OrderLineItemsRepository;
import com.jpa.amazon.ecommerce.orders.repository.OrdersRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OrderLineItemsServiceTest {

    @Mock
    private OrderLineItemsRepository orderLineItemsRepository;

    @Mock
    private OrdersRepository ordersRepository;

    @InjectMocks
    private OrderLineItemsService orderLineItemsService;

    private OrderLineItems orderLineItems = new OrderLineItems();
    private Orders orders = new Orders();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        orders.setId(1);
        orders.setTotalPrice(367);

        orderLineItems.setId(1);
        orderLineItems.setQuantity(2);
        orderLineItems.setPrice(34);
    }

    @Test
    public void testCreateOrderLineItem() {
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.ofNullable(orders));
        when(orderLineItemsRepository.save(any())).thenReturn(orderLineItems);

        orderLineItemsService.create(orderLineItems, orders.getId());

        assertEquals(1, orderLineItems.getId());
        assertEquals(2, orderLineItems.getQuantity());
    }

    @Test
    public void testUpdateOrderLineItem() {
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.ofNullable(orders));
        when(orderLineItemsRepository.findById(anyLong())).thenReturn(Optional.ofNullable(orderLineItems));

        orderLineItems.setId(2);
        orderLineItems.setQuantity(4);
        orderLineItemsService.update(orderLineItems.getId(), orders.getId(), orderLineItems);

        assertEquals(2, orderLineItems.getId());
        assertNotEquals(1, orderLineItems.getId());
        assertEquals(4, orderLineItems.getQuantity());
    }

    @Test
    public void testGetOrderLineItems() {
        orderLineItemsService.getAllByOrderId(orderLineItems.getId());

        assertEquals(1, orderLineItems.getId());
        assertNotEquals(2, orderLineItems.getId());
    }

    @Test
    public void testDeleteOrderLineItem() {
        OrderLineItems ol = new OrderLineItems();
        ol.setId(1L);
        Orders o = new Orders();
        o.setId(2L);
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.ofNullable(o));
        when(orderLineItemsRepository.findById(anyLong())).thenReturn(Optional.ofNullable(ol));

        orderLineItemsService.delete(o.getId(), ol.getId());

        assertEquals(0, ol.getQuantity());
    }

    @Test
    public void testGetOrderLineItemById() {

        when(ordersRepository.findById(anyLong())).thenReturn(Optional.ofNullable(orders));
        when(orderLineItemsRepository.findById(anyLong())).thenReturn(Optional.ofNullable(orderLineItems));

        orderLineItemsService.getById(orderLineItems.getId(), orders.getId());

        assertNotNull(orderLineItems);
    }
}
