package com.multitrans.wasalliya.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CreateTourDTO {
    private LocalDate date;
    private Double totalDistance;
    private Long vehicalId;
}
