package com.multitrans.wasalliya.repository;

import com.multitrans.wasalliya.enums.DeliveryStatus;
import com.multitrans.wasalliya.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
    List<Delivery> findAllByDeliveryStatus(DeliveryStatus status);
}
