package com.jpa.amazon.ecommerce.orders.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jpa.amazon.ecommerce.orders.repository.OrderLineItemsRepository;
import com.jpa.amazon.ecommerce.orders.repository.OrdersRepository;
import com.jpa.amazon.ecommerce.orders.service.OrderLineItemsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class ModelTest {

    @Test
    public void testConstructors() {

        OrderLineItemsRepository orderLineItemsRepository = null;
        OrdersRepository ordersRepository = null;

        OrderLineItemsService service = new OrderLineItemsService(orderLineItemsRepository, ordersRepository);

        assertNotNull(service);

    }

    @Test
    public void testAddressDetails() throws Exception {

        Address address = new Address("938 Wabash Ave", "45F", "Chicago", "IL", "60060", "USA");

        Assert.assertEquals("938 Wabash Ave", address.getStreet());
        Assert.assertEquals("45F", address.getApt());
        Assert.assertEquals("Chicago", address.getCity());
        Assert.assertEquals("IL", address.getState());
        Assert.assertEquals("60060", address.getZipCode());
        Assert.assertEquals("USA", address.getCountry());
    }

    @Test
    public void testShipmentDetails() throws Exception {
        List<OrderLineItems> orderLineItems = new ArrayList<>();

        Shipment shipment = new Shipment(1, orderLineItems, Date.valueOf("2018-09-12"), Date.valueOf("2018-09-12"));

        Assert.assertEquals(1, shipment.getId());
        Assert.assertEquals(orderLineItems, shipment.getOrderLineItems());
        Assert.assertEquals(Date.valueOf("2018-09-12"), shipment.getShippedDate());
        Assert.assertEquals(Date.valueOf("2018-09-12"), shipment.getDeliveryDate());

    }

    @Test
    public void testOrderDetails() throws Exception {
        long orderNumber;
        Address shippingAddress = new Address();
        float totalPrice;
        List<Shipment> shipment = new ArrayList<>();
        List<OrderLineItems> lineItems = new ArrayList<>();

        OrderDetails orderDetails = new OrderDetails(2344, shippingAddress, 545, shipment, lineItems);

        Assert.assertEquals(2344, orderDetails.getOrderNumber());
        Assert.assertEquals(shippingAddress, orderDetails.getShippingAddress());
        Assert.assertEquals(545, orderDetails.getTotalPrice());
        Assert.assertEquals(shipment, orderDetails.getShipment());
        Assert.assertEquals(lineItems, orderDetails.getLineItems());

    }
}
