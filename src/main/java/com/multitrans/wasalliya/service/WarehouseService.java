package com.multitrans.wasalliya.service;


import com.multitrans.wasalliya.dto.WarehouseDTO;
import com.multitrans.wasalliya.mapper.WarehouseMapper;
import com.multitrans.wasalliya.model.Warehouse;
import com.multitrans.wasalliya.repository.WarehouseRepository;
import jakarta.transaction.Transactional;

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

}
