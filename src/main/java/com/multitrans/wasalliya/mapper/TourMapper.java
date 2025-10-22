package com.multitrans.wasalliya.mapper;

import com.multitrans.wasalliya.dto.CreateTourDTO;
import com.multitrans.wasalliya.dto.TourDTO;
import com.multitrans.wasalliya.model.Tour;
import com.multitrans.wasalliya.model.Vehicale;

public class TourMapper {

    public static TourDTO toDTO(Tour tour) {
        TourDTO dto = new TourDTO();

        dto.setId(tour.getId());
        dto.setDate(tour.getDate());
        dto.setTotalDistance(tour.getTotalDistance());

        if (tour.getDeliveries() != null){
            dto.setDeliveryCount(tour.getDeliveries().size());
        }else{
            dto.setDeliveryCount(0);
        }

        if(tour.getVehicale() != null){
            dto.setVehicaleId(tour.getVehicale().getId());
            dto.setVehicaleName(tour.getVehicale().getVehicalType().toString());
        }

        return dto;
    }

    public static Tour toEntity(CreateTourDTO dto, Vehicale vehicale){

        Tour tour  = new Tour();

        tour.setTotalDistance(dto.getTotalDistance());
        tour.setDate(dto.getDate());

        tour.setVehicale(vehicale);

        return tour;

    }

}
