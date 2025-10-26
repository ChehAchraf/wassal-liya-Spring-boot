package com.multitrans.wasalliya.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="latitude" , nullable=false)
    private double latitude;

    @Column(name="logitude" , nullable=false)
    private double longitude;

    @Column(name="opening_hours" , nullable=false)
    private String openingHours;

    @OneToMany(mappedBy = "warehouse")
    private List<Tour> tours;

}
