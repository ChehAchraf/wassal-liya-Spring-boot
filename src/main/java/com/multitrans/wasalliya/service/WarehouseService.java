package com.multitrans.wasalliya.service;


import com.multitrans.wasalliya.dto.WarehouseDTO;
import com.multitrans.wasalliya.mapper.WarehouseMapper;
import com.multitrans.wasalliya.model.Warehouse;
import com.multitrans.wasalliya.repository.WarehouseRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

public class WarehouseService {
    private final WarehouseRepository warehouseRepo;
    private final WarehouseMapper wMapper;

    public WarehouseService(WarehouseRepository warehouserepository , WarehouseMapper warehousemapper ){
        this.warehouseRepo = warehouserepository;
        this.wMapper = warehousemapper;
    }

    @Transactional
    public WarehouseDTO createWarehouse(WarehouseDTO dto){
        Warehouse warehouseToSave = wMapper.toEntity(dto);
        Warehouse savedWarehouse =  warehouseRepo.save(warehouseToSave);
        return wMapper.toDTO(savedWarehouse);
    }

    @Transactional
    public WarehouseDTO findWarehouseById(Long id){
        Warehouse foundedWarehouse = warehouseRepo.findById(id).orElseThrow(NoSuchElementException::new);
        return wMapper.toDTO(foundedWarehouse);
    }

    @Transactional
    public String deleteWarehouseById(Long id){
        Warehouse warehouseToDelete = warehouseRepo.findById(id).orElseThrow(NoSuchElementException::new);
        warehouseRepo.deleteById(id);
        return "Ware house with id "+id+" Has been deleted Seccussfully";
    }

    @Transactional
    public WarehouseDTO updateWarehouseById(Long id, WarehouseDTO dto){
        Warehouse warehouseToEdit = warehouseRepo.findById(id).orElseThrow(NoSuchElementException::new);
        wMapper.updateFromDTO(dto,warehouseToEdit);
        return wMapper.toDTO(warehouseRepo.save(warehouseToEdit));
    }

}
