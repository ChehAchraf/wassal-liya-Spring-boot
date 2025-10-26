package com.multitrans.wasalliya.controller;

import com.multitrans.wasalliya.dto.WarehouseDTO;
import com.multitrans.wasalliya.mapper.WarehouseMapper;
import com.multitrans.wasalliya.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseSer;
    private  final WarehouseMapper wMapper;

    public WarehouseController(WarehouseService warehouseService,WarehouseMapper warehouseMapper){
        this.warehouseSer= warehouseService;
        this.wMapper = warehouseMapper;
    }

    @PostMapping("/")
    public ResponseEntity<WarehouseDTO> save(@RequestBody WarehouseDTO dto){
        return ResponseEntity.ok(warehouseSer.createWarehouse(dto));
    }
}
