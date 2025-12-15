package com.eugene.hospitalapi.repository;

import com.eugene.hospitalapi.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByEmailIgnoreCase(String email);
    List<Patient> findByNameContainingIgnoreCase(String name);
}
