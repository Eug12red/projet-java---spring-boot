package com.eugene.hospitalapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Simple pour un projet 3: String. (Tu pourras passer à enum plus tard)
    @Column(nullable = false)
    private String specialty;
}
