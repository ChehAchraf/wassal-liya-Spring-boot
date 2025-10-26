package com.multitrans.wasalliya.dto;

import java.time.LocalDate;
import java.util.List;

import com.multitrans.wasalliya.model.Delivery;
import com.multitrans.wasalliya.model.Vehicale;

import lombok.Data;

@Data
public class TourDTO {

    private Long id;
    private LocalDate date;
    private Double totalDistance;
    private List<Long> deliveryIds;
    private Long vehicaleId;

}
