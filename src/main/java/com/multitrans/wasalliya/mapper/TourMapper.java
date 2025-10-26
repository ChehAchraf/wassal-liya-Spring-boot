package com.multitrans.wasalliya.mapper;

import com.multitrans.wasalliya.dto.TourDTO;
import com.multitrans.wasalliya.model.Delivery;
import com.multitrans.wasalliya.model.Tour;
import com.multitrans.wasalliya.model.Vehicale;
import com.multitrans.wasalliya.repository.DeliveryRepository;
import com.multitrans.wasalliya.repository.VehicaleRepository;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TourMapper {

    private final VehicaleRepository vehicaleRepo;
    private final DeliveryRepository deliveryRepo;

    public TourMapper(VehicaleRepository vehicaleRepo, DeliveryRepository deliveryRepository) {
        this.vehicaleRepo = vehicaleRepo;
        this.deliveryRepo = deliveryRepository;
    }

    public TourDTO toDTO(Tour tour) {
        if (tour == null) {
            return null;
        }

        TourDTO dto = new TourDTO();
        dto.setId(tour.getId());
        dto.setDate(tour.getDate());
        dto.setTotalDistance(tour.getTotalDistance());

        if (tour.getVehicale() != null) {
            dto.setVehicaleId(tour.getVehicale().getId());
        } else {
            dto.setVehicaleId(null);
        }
        if (tour.getDeliveries() != null && !tour.getDeliveries().isEmpty()) {
            List<Long> ids = tour.getDeliveries().stream()
                    .map(Delivery::getId)
                    .collect(Collectors.toList());
            dto.setDeliveryIds(ids);
        } else {
            dto.setDeliveryIds(Collections.emptyList());
        }

        return dto;
    }

    public Tour toEntity(TourDTO dto) {
        if (dto == null) return null;

        Tour tour = new Tour();

        // 6. كنكوبيو الحقول البسيطة
        tour.setDate(dto.getDate());
        tour.setTotalDistance(dto.getTotalDistance());


        // 7. كنجيبو الفيهيكل الحقيقي من الباز دو دوني
        if (dto.getVehicaleId() != null) {
            Vehicale vehicale = vehicaleRepo.findById(dto.getVehicaleId())
                    .orElseThrow(() -> new RuntimeException("Vehicale not found with id: " + dto.getVehicaleId()));
            tour.setVehicale(vehicale);
        }

        if (dto.getDeliveryIds() != null && !dto.getDeliveryIds().isEmpty()) {
            List<Delivery> deliveries = deliveryRepo.findAllById(dto.getDeliveryIds());
            tour.setDeliveries(deliveries);
        }

        return tour;
    }

}