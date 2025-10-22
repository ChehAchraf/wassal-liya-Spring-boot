package com.multitrans.wasalliya.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TourDTO {

    private Long id;
    private LocalDate date;
    private Double totalDistance;

    private int deliveryCount;

    private Long vehicaleId;
    private String vehicaleName;


}
