package com.multitrans.wasalliya.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tours")
@NoArgsConstructor
@Data
public class Tour {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    @Column(name="total_distance" , nullable = false)
    private String totalDistance;
    
    @OneToMany( mappedBy="tour" , cascade= CascadeType.ALL , orphanRemoval=true )
    private List<Delivery> deliveries;

    @ManyToOne
    @JoinColumn(name = "vehicale_id")
    private Vehicale vehicale;
}
