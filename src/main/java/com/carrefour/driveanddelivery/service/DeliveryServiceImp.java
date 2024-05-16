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

        return deliverySlotRepository.findByDeliveryMethodAndBookedFalse(deliveryMethod);
    }

    @Override
    public DeliverySlot bookSlot(Long slotId) {

        DeliverySlot slot = deliverySlotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));
        if (slot.isBooked()) {
            throw new RuntimeException("Slot already booked");
        }
        slot.setBooked(true);
        return deliverySlotRepository.save(slot);
    }

    @Override
    public DeliverySlot createSlot(DeliveryMethod deliveryMethod, LocalDateTime startTime, LocalDateTime endTime) {
        DeliverySlot slot = new DeliverySlot();
        slot.setDeliveryMethod(deliveryMethod);
        slot.setStartTime(startTime);
        slot.setEndTime(endTime);
        slot.setBooked(false);
        return deliverySlotRepository.save(slot);
    }
}
