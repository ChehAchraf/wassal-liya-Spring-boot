package com.multitrans.wasalliya.service;

import java.util.List;

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

}
