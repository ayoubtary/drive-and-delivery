package com.carrefour.driveanddelivery.controller;

import com.carrefour.driveanddelivery.model.DeliveryMethod;
import com.carrefour.driveanddelivery.model.DeliverySlot;
import com.carrefour.driveanddelivery.service.DeliveryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeliveryControllerTest {

    @Mock
    private DeliveryService deliveryService;

    @InjectMocks
    private DeliveryController deliveryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAvailableDeliveryMethods() {
        ResponseEntity<DeliveryMethod[]> response = deliveryController.getAvailableDeliveryMethods();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(DeliveryMethod.values().length, response.getBody().length);
    }

    @Test
    void testGetAvailableSlots() {
        DeliverySlot slot1 = new DeliverySlot();
        slot1.setId(1L);
        slot1.setDeliveryMethod(DeliveryMethod.DELIVERY);
        slot1.setStartTime(LocalDateTime.now());
        slot1.setEndTime(LocalDateTime.now().plusHours(1));
        slot1.setBooked(false);

        DeliverySlot slot2 = new DeliverySlot();
        slot2.setId(2L);
        slot2.setDeliveryMethod(DeliveryMethod.DELIVERY);
        slot2.setStartTime(LocalDateTime.now().plusHours(2));
        slot2.setEndTime(LocalDateTime.now().plusHours(3));
        slot2.setBooked(false);

        when(deliveryService.getAvailableSlots(DeliveryMethod.DELIVERY)).thenReturn(Arrays.asList(slot1, slot2));

        ResponseEntity<List<DeliverySlot>> response = deliveryController.getAvailableSlots(DeliveryMethod.DELIVERY);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testBookSlot() {
        DeliverySlot slot = new DeliverySlot();
        slot.setId(1L);
        slot.setDeliveryMethod(DeliveryMethod.DELIVERY);
        slot.setStartTime(LocalDateTime.now());
        slot.setEndTime(LocalDateTime.now().plusHours(1));
        slot.setBooked(false);

        when(deliveryService.bookSlot(1L)).thenReturn(slot);

        ResponseEntity<DeliverySlot> response = deliveryController.bookSlot(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(slot, response.getBody());
    }

    @Test
    void testCreateSlot() {
        DeliverySlot slot = new DeliverySlot();
        slot.setId(1L);
        slot.setDeliveryMethod(DeliveryMethod.DELIVERY);
        slot.setStartTime(LocalDateTime.now());
        slot.setEndTime(LocalDateTime.now().plusHours(1));
        slot.setBooked(false);

        when(deliveryService.createSlot(DeliveryMethod.DELIVERY, slot.getStartTime(), slot.getEndTime())).thenReturn(slot);

        ResponseEntity<DeliverySlot> response = deliveryController.createSlot(DeliveryMethod.DELIVERY, slot.getStartTime(), slot.getEndTime());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(slot, response.getBody());
    }
}