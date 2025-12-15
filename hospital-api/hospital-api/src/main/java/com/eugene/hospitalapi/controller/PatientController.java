package com.eugene.hospitalapi.controller;

import com.eugene.hospitalapi.model.dto.PatientCreateRequest;
import com.eugene.hospitalapi.model.dto.PatientResponse;
import com.eugene.hospitalapi.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping
    public PatientResponse create(@Valid @RequestBody PatientCreateRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<PatientResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PatientResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    public List<PatientResponse> search(@RequestParam String q) {
        return service.searchByName(q);
    }
}
