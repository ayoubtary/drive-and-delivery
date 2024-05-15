package com.carrefour.driveanddelivery.service;

import com.carrefour.driveanddelivery.model.DeliveryMethod;
import com.carrefour.driveanddelivery.model.DeliverySlot;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public interface DeliveryService {
    public List<DeliverySlot> getAvailableSlots(DeliveryMethod deliveryMethod);
    public DeliverySlot bookSlot(Long slotId);
    public DeliverySlot createSlot(DeliveryMethod deliveryMethod, LocalDateTime startTime, LocalDateTime endTime);
}
