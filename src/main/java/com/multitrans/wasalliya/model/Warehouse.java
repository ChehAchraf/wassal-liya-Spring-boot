package com.multitrans.wasalliya.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "tour_id")
    private Tour tour;

}
