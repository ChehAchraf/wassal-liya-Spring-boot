package com.multitrans.wasalliya.service;

import com.multitrans.wasalliya.model.dto.DeliveryDTO;
import com.multitrans.wasalliya.model.mapper.DeliveryMapper;
import com.multitrans.wasalliya.model.Delivery;
import com.multitrans.wasalliya.repository.DeliveryRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeliveryService {

    private final DeliveryRepository deliveryRepo;
    private final DeliveryMapper dMapper;


    public DeliveryService(DeliveryRepository deliveryRepo, DeliveryMapper deliveryMapper){
        this.deliveryRepo = deliveryRepo;
        this.dMapper = deliveryMapper;
    }

    public List<DeliveryDTO> getAllDeliveries(){
        List<Delivery> allDeliveries = deliveryRepo.findAll();

        return allDeliveries.stream().map(dMapper::toDTO).collect(Collectors.toList());

    }

    public DeliveryDTO createDelivery(DeliveryDTO dto){
        Delivery deliveryToSave = dMapper.toEntity(dto);
        Delivery savedDelivery = deliveryRepo.save(deliveryToSave);
        return dMapper.toDTO(savedDelivery);
    }

    public DeliveryDTO findDeliveryById(Long id){
        Optional<Delivery> foundedDelivery = deliveryRepo.findById(id);
        return  foundedDelivery.map(dMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("the Delivery with id : " +id+" is not found"));
    }

    public String deleteDeliveryById(Long id){
        if(!deliveryRepo.existsById(id)){
            throw new NoSuchElementException("the Delivery with id : " +id+" is not found");
        }

        deliveryRepo.deleteById(id);
        return "Delivery Deleted Successfully";
    }

    public DeliveryDTO editDeliveryById(Long id, DeliveryDTO dto){
        Delivery deliveryToUpdate = deliveryRepo.findById(id).orElseThrow(() -> new NoSuchElementException());
        dMapper.updateFromDTO(dto,deliveryToUpdate);
        Delivery savedEntity = deliveryRepo.save(deliveryToUpdate);
        return dMapper.toDTO(savedEntity);
    }


}
