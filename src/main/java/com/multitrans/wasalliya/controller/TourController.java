package com.multitrans.wasalliya.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.multitrans.wasalliya.dto.TourDTO;
import com.multitrans.wasalliya.mapper.TourMapper;
import com.multitrans.wasalliya.optimizer.TourOptimizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multitrans.wasalliya.model.Tour;
import com.multitrans.wasalliya.service.TourService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.PutMapping;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/tour")
public class TourController {

    private final TourService tourSer;
    private final ObjectMapper objectmap;
    private final TourMapper tourMapper;


    public TourController(TourService tourservice, ObjectMapper objectmapper, TourMapper tourMapper) {
        this.tourSer = tourservice;
        this.objectmap = objectmapper;
        this.tourMapper = tourMapper;
    }

    // get all tours
    @GetMapping("/")
    @Operation(summary = "get all tours", description = "get all the the tours")
    public List<Tour> index() {
        return tourSer.getAllTour();
    }

    // save a tour 
    @PostMapping
    public ResponseEntity<TourDTO> save(@RequestBody TourDTO tourDTO) {
        var savedTour =  tourSer.saveTour(tourDTO);
        return ResponseEntity.ok(savedTour);
    }

    // find tour by id 
    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        Optional<Tour> result = tourSer.findTourById(id);

        return ResponseEntity.ok(result.get());
    }

    // delete a tour
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Tour> tour = tourSer.findTourById(id);

        tourSer.deleteTour(tour.get());
        return ResponseEntity.ok("tour with id " + id + " has been deleted seccussfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Tour tour) {
        Optional<Tour> updatedTour = tourSer.updateTour(id, tour);

        return ResponseEntity.ok(updatedTour.get());
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody Map<String, Object> updates) throws JsonMappingException {
//        Optional<Tour> existingTourOpt = tourSer.findTourById(id);
//
//
//        Tour existingTour = existingTourOpt.get();
//
//
//        objectmap.updateValue(existingTour, updates);
//
//        tourSer.saveTour(existingTour);
//        return ResponseEntity.ok(existingTour);
//    }

}