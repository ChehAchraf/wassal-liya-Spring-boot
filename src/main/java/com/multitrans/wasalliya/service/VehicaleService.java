package com.multitrans.wasalliya.service;

import com.multitrans.wasalliya.dto.VehicalDTO;
import com.multitrans.wasalliya.mapper.VehicaleMapper;
import com.multitrans.wasalliya.model.Vehicale;
import com.multitrans.wasalliya.repository.VehicaleRepository;

import jakarta.transaction.Transactional;

import java.util.Optional;

public class VehicaleService {
    
    private final VehicaleRepository vehicaleRepo;
    private final VehicaleMapper vmapper;

    public VehicaleService(VehicaleRepository vehicaleRepository, VehicaleMapper vehicaleMapper){
        this.vehicaleRepo = vehicaleRepository;
        this.vmapper = vehicaleMapper;
    }

    @Transactional
    public VehicalDTO crateVehicale(VehicalDTO dto){
        Vehicale vehicale = vmapper.toEntity(dto);
        Vehicale savedVehicale = vehicaleRepo.save(vehicale);
        return vmapper.toDTO(savedVehicale);
    }

    @Transactional()
    public VehicalDTO findById(Long id){
        Vehicale vehicale = vehicaleRepo.findById(id).orElseThrow(()->new RuntimeException("Vehical not found"));
        return vmapper.toDTO(vehicale);
    }

        @Transactional
        public VehicalDTO updateVehicla(Long id, VehicalDTO dto){
            Vehicale vehicaleToUpdate = vehicaleRepo.findById(id)
                    .orElseThrow(()-> new RuntimeException("Vehiclae with this id :"+id+" is not found" ));
            vmapper.updateFromDto(dto,vehicaleToUpdate);
            Vehicale updatedVehicale = vehicaleRepo.save(vehicaleToUpdate);
            return vmapper.toDTO(updatedVehicale);
        }

}
