package com.multitrans.wasalliya.model.dto;

import java.util.List;

public record WarehouseDTO(
        Long id,
        Double latitude,
        Double longitude,
        String openingHours,
        List<Long> tourIds
) {
}
