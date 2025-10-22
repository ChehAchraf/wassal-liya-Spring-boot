package com.multitrans.wasalliya.service;

import java.util.List;
import java.util.Optional;

import com.multitrans.wasalliya.model.Tour;
import com.multitrans.wasalliya.repository.TourRepository;

public class TourService {

    private final TourRepository tourRepo;

    public TourService(TourRepository tourRepository){
        this.tourRepo = tourRepository;
    }

    public List<Tour> getAllTour(){
        return tourRepo.findAll();
    }

    public Tour saveTour(Tour tour){
        return tourRepo.save(tour);
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
