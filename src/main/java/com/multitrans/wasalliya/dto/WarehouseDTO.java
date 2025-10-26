package com.multitrans.wasalliya.dto;

import java.util.List;

public record WarehouseDTO(
        Long id,
        Double latitude,
        Double longitude,
        String openingHours,
        List<Long> tourIds
) {
}
