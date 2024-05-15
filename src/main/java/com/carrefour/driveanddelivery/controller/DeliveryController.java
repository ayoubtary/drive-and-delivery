package com.carrefour.driveanddelivery.controller;

import com.carrefour.driveanddelivery.model.DeliveryMethod;
import com.carrefour.driveanddelivery.model.DeliverySlot;
import com.carrefour.driveanddelivery.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@Tag(name = "Delivery API", description = "API for managing delivery slots and bookings")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @Operation(summary = "Get available delivery methods", description = "Get all available delivery methods")
    @GetMapping("/methods")
    public ResponseEntity<DeliveryMethod[]> getAvailableDeliveryMethods() {
        return new ResponseEntity<>(DeliveryMethod.values(), HttpStatus.OK);
    }

    @Operation(summary = "Get available delivery slots", description = "Get all available delivery slots for a specific delivery method")
    @GetMapping("/slots")
    public ResponseEntity<List<DeliverySlot>> getAvailableSlots(
            @Parameter(description = "Delivery method", required = true)
            @RequestParam DeliveryMethod deliveryMethod) {
        List<DeliverySlot> availableSlots = deliveryService.getAvailableSlots(deliveryMethod);
        return new ResponseEntity<>(availableSlots, HttpStatus.OK);
    }

    @Operation(summary = "Book a delivery slot", description = "Book a specific delivery slot by its ID")
    @PostMapping("/book/{slotId}")
    public ResponseEntity<DeliverySlot> bookSlot(
            @Parameter(description = "ID of the delivery slot to book", required = true)
            @PathVariable Long slotId) {
        DeliverySlot bookedSlot = deliveryService.bookSlot(slotId);
        return new ResponseEntity<>(bookedSlot, HttpStatus.OK);
    }

    @Operation(summary = "Create a new delivery slot", description = "Create a new delivery slot for a specific method and time range")
    @PostMapping("/slots")
    public ResponseEntity<DeliverySlot> createSlot(
            @Parameter(description = "Delivery method", required = true)
            @RequestParam DeliveryMethod deliveryMethod,
            @Parameter(description = "Start time of the delivery slot", required = true)
            @RequestParam LocalDateTime startTime,
            @Parameter(description = "End time of the delivery slot", required = true)
            @RequestParam LocalDateTime endTime) {
        DeliverySlot newSlot = deliveryService.createSlot(deliveryMethod, startTime, endTime);
        return new ResponseEntity<>(newSlot, HttpStatus.CREATED);
    }
}
