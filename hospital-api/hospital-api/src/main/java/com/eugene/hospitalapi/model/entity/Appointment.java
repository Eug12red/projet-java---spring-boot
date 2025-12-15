package com.eugene.hospitalapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Table(
        name = "appointments",
        uniqueConstraints = {
                // Règle DB: un médecin ne peut pas avoir 2 RDV au même moment
                @UniqueConstraint(name = "uk_doctor_datetime", columnNames = {"doctor_id", "date_time"})
        }
)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Un patient peut avoir plusieurs RDV
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Un médecin peut avoir plusieurs RDV
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;
}
