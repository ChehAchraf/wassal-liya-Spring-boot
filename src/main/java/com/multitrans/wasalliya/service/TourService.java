package com.multitrans.wasalliya.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.multitrans.wasalliya.model.dto.TourDTO;
import com.multitrans.wasalliya.enums.DeliveryStatus;
import com.multitrans.wasalliya.model.mapper.TourMapper;
import com.multitrans.wasalliya.model.Delivery;
import com.multitrans.wasalliya.model.Tour;
import com.multitrans.wasalliya.model.Vehicale;
import com.multitrans.wasalliya.model.Warehouse;
import com.multitrans.wasalliya.optimizer.TourOptimizer;
import com.multitrans.wasalliya.repository.DeliveryRepository;
import com.multitrans.wasalliya.repository.TourRepository;
import com.multitrans.wasalliya.repository.VehicaleRepository;
import com.multitrans.wasalliya.repository.WarehouseRepository;
import com.multitrans.wasalliya.util.DistanceCalculator;
import org.springframework.transaction.annotation.Transactional;

public class TourService {

    private final TourRepository tourRepo;
    private final TourMapper tourmapper;
    private final TourOptimizer tourOptimizer;
    private final DeliveryRepository deliveryRepo;
    private final VehicaleRepository vehicaleRepo;
    private final WarehouseRepository warehouseRepo;

    public TourService(TourRepository tourRepository, TourMapper topurmapper, TourOptimizer tourOptimizer, DeliveryRepository deliveryRepo, VehicaleRepository vehicaleRepo, WarehouseRepository warehouseRepo){
        this.tourRepo = tourRepository;
        this.tourmapper = topurmapper;
        this.tourOptimizer = tourOptimizer;
        this.deliveryRepo = deliveryRepo;
        this.vehicaleRepo = vehicaleRepo;
        this.warehouseRepo = warehouseRepo;
    }

    public List<Delivery> getOptimizedTour(Long warehouseId, Long vehicaleId){

        Warehouse warehouse = warehouseRepo.findById(warehouseId)
                .orElseThrow(NoSuchElementException::new);

        Vehicale vehicle = vehicaleRepo.findById(vehicaleId)
                .orElseThrow(NoSuchElementException::new);

        List<Delivery> pendingDeliveries = deliveryRepo.findAllByDeliveryStatus(DeliveryStatus.PENDING);
        List<Delivery> orderedDeliveries = this.tourOptimizer.calculateOptimalTour(warehouse, pendingDeliveries, vehicle);
        return orderedDeliveries;
        

    }

    @Transactional
    public List<Tour> getAllTour(){
        return tourRepo.findAll();
    }

    @Transactional
    public TourDTO saveTour(TourDTO dto){
        Tour tourToSave = tourmapper.toEntity(dto);
        Tour savedTour = tourRepo.save(tourToSave);
        return tourmapper.toDTO(savedTour);
    }

    @Transactional
    public Optional<Tour> findTourById(Long tourId){
        return tourRepo.findById(tourId);
    }

    @Transactional
    public void deleteTour(Tour tour){
        tourRepo.delete(tour);
    }

    @Transactional
    public Optional<Tour> updateTour(Long id, Tour newTourData){
        Optional<Tour> existingTour = tourRepo.findById(id);

        if(existingTour.isEmpty()){
            return Optional.empty();
        }

        Tour tour = existingTour.get();

        tour.setDate(newTourData.getDate());
        tour.setTotalDistance(newTourData.getTotalDistance());
        tour.setVehicale(newTourData.getVehicale());

        tourRepo.save(tour);
        return Optional.of(tour);
    }

    public double getTotalDistance(Warehouse warehouse, List<Delivery> orderedTour) {
        if (orderedTour == null || orderedTour.isEmpty()) {
            return 0.0;
        }
        double totalDistance = 0.0;
        double whLat = warehouse.getLatitude();
        double whLon = warehouse.getLongitude();
        Delivery firstDelivery = orderedTour.get(0);
        totalDistance += DistanceCalculator.calculateDistance(
                whLat, whLon,
                firstDelivery.getLatitude(), firstDelivery.getLongitude()
        );

        for (int i = 0; i < orderedTour.size() - 1; i++) {
            Delivery current = orderedTour.get(i);
            Delivery next = orderedTour.get(i + 1);

            totalDistance += DistanceCalculator.calculateDistance(
                    current.getLatitude(), current.getLongitude(),
                    next.getLatitude(), next.getLongitude()
            );
        }

        Delivery lastDelivery = orderedTour.get(orderedTour.size() - 1);
        totalDistance += DistanceCalculator.calculateDistance(
                lastDelivery.getLatitude(), lastDelivery.getLongitude(),
                whLat, whLon
        );

        return totalDistance;
        
    }

    public Object getOptimizerName() {
        if (this.tourOptimizer == null) {
            return "No Optimizer Injected";
        }
        return this.tourOptimizer.getClass().getSimpleName();
    }
}