package com.eugene.hospitalapi.service;

import com.eugene.hospitalapi.model.dto.DoctorCreateRequest;
import com.eugene.hospitalapi.model.dto.DoctorResponse;
import com.eugene.hospitalapi.model.entity.Doctor;
import com.eugene.hospitalapi.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import com.eugene.hospitalapi.exception.ResourceNotFoundException;


import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository repo;

    public DoctorService(DoctorRepository repo) {
        this.repo = repo;
    }

    public DoctorResponse create(DoctorCreateRequest req) {
        Doctor saved = repo.save(Doctor.builder()
                .name(req.name())
                .specialty(req.specialty())
                .build());

        return new DoctorResponse(saved.getId(), saved.getName(), saved.getSpecialty());
    }

    public DoctorResponse getById(Long id) {
        Doctor d = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found: " + id));
        return new DoctorResponse(d.getId(), d.getName(), d.getSpecialty());
    }

    public List<DoctorResponse> getAll() {
        return repo.findAll().stream()
                .map(d -> new DoctorResponse(d.getId(), d.getName(), d.getSpecialty()))
                .toList();
    }

    // utile pour AppointmentService
    public Doctor requireEntity(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found: " + id));
    }
}
