package com.carrefour.driveanddelivery.repository;

import com.carrefour.driveanddelivery.model.DeliveryMethod;
import com.carrefour.driveanddelivery.model.DeliverySlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverySlotRepository extends JpaRepository<DeliverySlot, Long> {
    List<DeliverySlot> findByDeliveryMethodAndBookedFalse(DeliveryMethod deliveryMethod);
}

