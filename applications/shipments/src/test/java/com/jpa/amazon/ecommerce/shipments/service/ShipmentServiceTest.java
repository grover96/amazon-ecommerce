package com.jpa.amazon.ecommerce.shipments.service;

import com.jpa.amazon.ecommerce.shipments.domain.Shipment;
import com.jpa.amazon.ecommerce.shipments.repository.ShipmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = SecurityConfig.class)
public class ShipmentServiceTest {

    @Mock
    ShipmentRepository shipmentRepository;

    @InjectMocks
    private ShipmentService shipmentService;

    private Shipment shipment = new Shipment();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        shipment.setId(1L);
        shipment.setDeliveryDate(Date.valueOf("2018-07-20"));
        shipment.setShippingAddress(1);
    }

    @Test
    public void testCreateShipment() {
        when(shipmentRepository.save(any())).thenReturn(shipment);

        shipmentService.create(shipment);

        assertEquals(1, shipment.getId());
        assertEquals(Date.valueOf("2018-07-20"), shipment.getDeliveryDate());
        assertEquals(1, shipment.getShippingAddress());
    }

    @Test
    public void testUpdateShipment() {
        when(shipmentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(shipment));

        shipment.setDeliveryDate(Date.valueOf("2018-07-21"));
        shipment.setShippingAddress(2);
        shipmentService.update(Long.valueOf(1), shipment);

        assertEquals(2, shipment.getShippingAddress());
        assertNotEquals(1, shipment.getShippingAddress());
        assertEquals(Date.valueOf("2018-07-21"), shipment.getDeliveryDate());
    }

    @Test
    public void testGetShipments() {
        shipmentService.getAll();

        assertEquals(1, shipment.getId());
        assertNotEquals(2, shipment.getId());
    }

    @Test
    public void testDeleteShipment() {
        Shipment s = new Shipment();
        s.setId(1L);
        when(shipmentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(s));

        shipmentService.delete(s.getId());

        assertNull(s.getDeliveryDate());
        assertNull(s.getShippedDate());

    }

    @Test
    public void testGetShipmentById() {
        when(shipmentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(shipment));

        shipmentService.getById(shipment.getId());

        assertNotNull(shipment);
    }

    @Test
    public void testAllOrdersForAccount() throws IOException {
        when(shipmentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(shipment));

        shipmentService.getAllOrdersForAccount(shipment.getId());

        assertNotNull(shipment);
    }

}
