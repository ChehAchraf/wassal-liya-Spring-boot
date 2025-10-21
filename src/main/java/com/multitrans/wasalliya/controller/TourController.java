package com.multitrans.wasalliya.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multitrans.wasalliya.model.Tour;
import com.multitrans.wasalliya.service.TourService;

@RestController
@RequestMapping("/tour")
public class TourController {

    private final TourService tourSer;

    public TourController(TourService tourservice) {
        this.tourSer = tourservice;
    }

    // get all tours
    @GetMapping("/")
    public List<Tour> index(){
        return tourSer.getAllTour();
    }

    // save a tour 
    @PostMapping
    public Tour save(@RequestBody Tour tour ){
        return tourSer.saveTour(tour);
    }

}
