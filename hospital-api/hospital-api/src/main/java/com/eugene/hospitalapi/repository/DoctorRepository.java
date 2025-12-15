package com.eugene.hospitalapi.repository;

import com.eugene.hospitalapi.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {}
