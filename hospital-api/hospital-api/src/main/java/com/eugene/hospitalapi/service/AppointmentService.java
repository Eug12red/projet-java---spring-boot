package com.eugene.hospitalapi.service;

import com.eugene.hospitalapi.exception.DoctorUnavailableException;
import com.eugene.hospitalapi.model.dto.AppointmentCreateRequest;
import com.eugene.hospitalapi.model.dto.AppointmentResponse;
import com.eugene.hospitalapi.model.entity.Appointment;
import com.eugene.hospitalapi.model.entity.Doctor;
import com.eugene.hospitalapi.model.entity.Patient;
import com.eugene.hospitalapi.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import com.eugene.hospitalapi.exception.ResourceNotFoundException;


import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository repo;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public AppointmentService(AppointmentRepository repo, PatientService patientService, DoctorService doctorService) {
        this.repo = repo;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    public AppointmentResponse create(AppointmentCreateRequest req) {

        if (repo.existsByDoctorIdAndDateTime(req.doctorId(), req.dateTime())) {
            throw new DoctorUnavailableException("Doctor already booked at this time.");
        }

        Patient patient = patientService.requireEntity(req.patientId());
        Doctor doctor = doctorService.requireEntity(req.doctorId());

        Appointment saved = repo.save(Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .dateTime(req.dateTime())
                .build());

        return toResponse(saved);
    }

    public List<AppointmentResponse> getAll() {
        return repo.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    private AppointmentResponse toResponse(Appointment a) {
        return new AppointmentResponse(
                a.getId(),
                a.getPatient().getId(),
                a.getPatient().getName(),
                a.getDoctor().getId(),
                a.getDoctor().getName(),
                a.getDoctor().getSpecialty(),
                a.getDateTime()
        );
    }
}
