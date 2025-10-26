package com.multitrans.wasalliya.mapper;

import com.multitrans.wasalliya.dto.WarehouseDTO;
import com.multitrans.wasalliya.model.Tour;
import com.multitrans.wasalliya.model.Warehouse;
import com.multitrans.wasalliya.repository.TourRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class WarehouseMapper {
    private final TourRepository tourRepo;

    public WarehouseMapper(TourRepository tourRepository){
        this.tourRepo = tourRepository;
    }

    public WarehouseDTO toDTO(Warehouse entity){
        if(entity == null) return null;
        
        List<Long> tourIds = entity.getTours() != null 
                ? entity.getTours().stream().map(Tour::getId).collect(Collectors.toList())
                : null;
        
        return new WarehouseDTO(
                entity.getId(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getOpeningHours(),
                tourIds
        );
    }

    public Warehouse toEntity (WarehouseDTO dto){
        Warehouse warehouse = new Warehouse();

        warehouse.setId(dto.id());
        warehouse.setLatitude(dto.latitude());
        warehouse.setLongitude(dto.longitude());
        warehouse.setOpeningHours(dto.openingHours());
        if(dto.tourIds() != null && !dto.tourIds().isEmpty()){
            List<Tour> tourIDs = dto.tourIds().stream()
                    .map(id -> tourRepo.findById(id).orElseThrow(NoSuchElementException::new)).toList();
            warehouse.setTours(tourIDs);
        }
        return warehouse;
    }
}
