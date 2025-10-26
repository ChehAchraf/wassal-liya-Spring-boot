package com.multitrans.wasalliya.controller;

import com.multitrans.wasalliya.dto.WarehouseDTO;
import com.multitrans.wasalliya.mapper.WarehouseMapper;
import com.multitrans.wasalliya.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/find/{id}")
    public ResponseEntity<WarehouseDTO> find(@PathVariable Long id){
        return ResponseEntity.ok(warehouseSer.findWarehouseById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(warehouseSer.deleteWarehouseById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<WarehouseDTO> update(@RequestBody WarehouseDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(warehouseSer.updateWarehouseById(id,dto));
    }

}
