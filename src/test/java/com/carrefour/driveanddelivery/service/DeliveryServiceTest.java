package com.carrefour.driveanddelivery.service;


import com.carrefour.driveanddelivery.model.DeliveryMethod;
import com.carrefour.driveanddelivery.model.DeliverySlot;
import com.carrefour.driveanddelivery.repository.DeliverySlotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeliveryServiceTest {

    @Mock
    private DeliverySlotRepository deliverySlotRepository;

    @InjectMocks
    private DeliveryServiceImp deliveryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deliveryService = new DeliveryServiceImp(deliverySlotRepository);
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

        when(deliverySlotRepository.findByDeliveryMethodAndBookedFalse(DeliveryMethod.DELIVERY))
                .thenReturn(Arrays.asList(slot1, slot2));

        List<DeliverySlot> availableSlots = deliveryService.getAvailableSlots(DeliveryMethod.DELIVERY);
        assertEquals(2, availableSlots.size());
        assertFalse(availableSlots.get(0).isBooked());
        assertFalse(availableSlots.get(1).isBooked());
    }

    @Test
    void testBookSlot() {
        DeliverySlot slot = new DeliverySlot();
        slot.setId(1L);
        slot.setDeliveryMethod(DeliveryMethod.DELIVERY);
        slot.setStartTime(LocalDateTime.now());
        slot.setEndTime(LocalDateTime.now().plusHours(1));
        slot.setBooked(false);

        when(deliverySlotRepository.findById(1L)).thenReturn(Optional.of(slot));
        when(deliverySlotRepository.save(slot)).thenReturn(slot);

        DeliverySlot bookedSlot = deliveryService.bookSlot(1L);
        assertTrue(bookedSlot.isBooked());

        verify(deliverySlotRepository, times(1)).findById(1L);
        verify(deliverySlotRepository, times(1)).save(slot);
    }

    @Test
    void testBookSlotSlotNotFound() {
        when(deliverySlotRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            deliveryService.bookSlot(1L);
        });

        assertEquals("Slot not found", exception.getMessage());
        verify(deliverySlotRepository, times(1)).findById(1L);
    }

    @Test
    void testBookSlotAlreadyBooked() {
        DeliverySlot slot = new DeliverySlot();
        slot.setId(1L);
        slot.setDeliveryMethod(DeliveryMethod.DELIVERY);
        slot.setStartTime(LocalDateTime.now());
        slot.setEndTime(LocalDateTime.now().plusHours(1));
        slot.setBooked(true);

        when(deliverySlotRepository.findById(1L)).thenReturn(Optional.of(slot));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            deliveryService.bookSlot(1L);
        });

        assertEquals("Slot already booked", exception.getMessage());
        verify(deliverySlotRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateSlot() {
        DeliverySlot slot = new DeliverySlot();
        slot.setId(1L);
        slot.setDeliveryMethod(DeliveryMethod.DELIVERY);
        slot.setStartTime(LocalDateTime.now());
        slot.setEndTime(LocalDateTime.now().plusHours(1));
        slot.setBooked(false);

        when(deliverySlotRepository.save(any(DeliverySlot.class))).thenReturn(slot);

        DeliverySlot createdSlot = deliveryService.createSlot(DeliveryMethod.DELIVERY, slot.getStartTime(), slot.getEndTime());
        assertEquals(slot.getDeliveryMethod(), createdSlot.getDeliveryMethod());
        assertEquals(slot.getStartTime(), createdSlot.getStartTime());
        assertEquals(slot.getEndTime(), createdSlot.getEndTime());
        assertFalse(createdSlot.isBooked());

        verify(deliverySlotRepository, times(1)).save(any(DeliverySlot.class));
    }
}
