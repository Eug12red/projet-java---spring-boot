package com.eugene.hospitalapi.controller;

import com.eugene.hospitalapi.model.dto.DoctorCreateRequest;
import com.eugene.hospitalapi.model.dto.DoctorResponse;
import com.eugene.hospitalapi.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @PostMapping
    public DoctorResponse create(@Valid @RequestBody DoctorCreateRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<DoctorResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DoctorResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
