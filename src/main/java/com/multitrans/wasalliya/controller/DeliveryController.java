package com.multitrans.wasalliya.controller;

import com.multitrans.wasalliya.dto.DeliveryDTO;
import com.multitrans.wasalliya.mapper.DeliveryMapper;
import com.multitrans.wasalliya.repository.DeliveryRepository;
import com.multitrans.wasalliya.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryService deliverySer;

    public DeliveryController(DeliveryService deliveryService ){
        this.deliverySer = deliveryService;
    }

    @GetMapping("")
    public ResponseEntity<List<DeliveryDTO>> index(){
        return ResponseEntity.ok(deliverySer.getAllDeliveries());
    }

    @PostMapping("/create")
    public ResponseEntity<DeliveryDTO> create(@RequestBody DeliveryDTO dto){
        return  ResponseEntity.ok(deliverySer.createDelivery(dto));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DeliveryDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(deliverySer.findDeliveryById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        return ResponseEntity.ok(deliverySer.deleteDeliveryById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DeliveryDTO> edit(@RequestBody DeliveryDTO dto, Long id){
        return ResponseEntity.ok(deliverySer.editDeliveryById(id,dto));
    }


}
