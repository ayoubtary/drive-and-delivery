package com.carrefour.driveanddelivery.service;

import com.carrefour.driveanddelivery.model.DeliveryMethod;
import com.carrefour.driveanddelivery.model.DeliverySlot;
import com.carrefour.driveanddelivery.repository.DeliverySlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class DeliveryServiceImp  implements DeliveryService{
    private final DeliverySlotRepository deliverySlotRepository;

    @Autowired
    public DeliveryServiceImp(DeliverySlotRepository deliverySlotRepository) {
        this.deliverySlotRepository = deliverySlotRepository;
    }
    @Override
    public List<DeliverySlot> getAvailableSlots(DeliveryMethod deliveryMethod) {
        return null;
    }

    @Override
    public DeliverySlot bookSlot(Long slotId) {
        return null;
    }

    @Override
    public DeliverySlot createSlot(DeliveryMethod deliveryMethod, LocalDateTime startTime, LocalDateTime endTime) {
        return null;
    }
}
