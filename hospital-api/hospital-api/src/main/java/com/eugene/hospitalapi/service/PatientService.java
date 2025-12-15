package com.eugene.hospitalapi.service;

import com.eugene.hospitalapi.exception.BadRequestException;
import com.eugene.hospitalapi.model.dto.PatientCreateRequest;
import com.eugene.hospitalapi.model.dto.PatientResponse;
import com.eugene.hospitalapi.model.entity.Patient;
import com.eugene.hospitalapi.repository.PatientRepository;
import org.springframework.stereotype.Service;
import com.eugene.hospitalapi.exception.ResourceNotFoundException;


import java.util.List;

@Service
public class PatientService {

    private final PatientRepository repo;

    public PatientService(PatientRepository repo) {
        this.repo = repo;
    }

    public PatientResponse create(PatientCreateRequest req) {
        if (repo.existsByEmailIgnoreCase(req.email())) {
            throw new BadRequestException("Email already used: " + req.email());
        }

        Patient saved = repo.save(Patient.builder()
                .name(req.name())
                .email(req.email())
                .build());

        return new PatientResponse(saved.getId(), saved.getName(), saved.getEmail());
    }

    public PatientResponse getById(Long id) {
        Patient p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + id));
        return new PatientResponse(p.getId(), p.getName(), p.getEmail());
    }

    public List<PatientResponse> getAll() {
        return repo.findAll().stream()
                .map(p -> new PatientResponse(p.getId(), p.getName(), p.getEmail()))
                .toList();
    }

    public List<PatientResponse> searchByName(String q) {
        return repo.findByNameContainingIgnoreCase(q).stream()
                .map(p -> new PatientResponse(p.getId(), p.getName(), p.getEmail()))
                .toList();
    }

    // utile pour AppointmentService
    public Patient requireEntity(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + id));
    }
}
