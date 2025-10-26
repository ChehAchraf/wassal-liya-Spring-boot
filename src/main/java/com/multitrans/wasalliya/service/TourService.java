package com.multitrans.wasalliya.service;

import java.util.List;
import java.util.Optional;

import com.multitrans.wasalliya.dto.TourDTO;
import com.multitrans.wasalliya.mapper.TourMapper;
import com.multitrans.wasalliya.model.Tour;
import com.multitrans.wasalliya.repository.TourRepository;
import org.springframework.transaction.annotation.Transactional;

public class TourService {

    private final TourRepository tourRepo;
    private final TourMapper tourmapper;

    public TourService(TourRepository tourRepository, TourMapper topurmapper){
        this.tourRepo = tourRepository;
        this.tourmapper = topurmapper;
    }

    @Transactional
    public List<Tour> getAllTour(){
        return tourRepo.findAll();
    }

    public TourDTO saveTour(TourDTO dto){
        Tour tourToSave = tourmapper.toEntity(dto);
        Tour savedTour = tourRepo.save(tourToSave);
        return tourmapper.toDTO(savedTour);
    }

    public Optional<Tour> findTourById(Long tourId){
        return tourRepo.findById(tourId);
    }

    public void deleteTour(Tour tour){
        tourRepo.delete(tour);
    }

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
}