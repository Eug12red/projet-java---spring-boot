package com.eugene.hospitalapi.controller;

import com.eugene.hospitalapi.model.dto.AppointmentCreateRequest;
import com.eugene.hospitalapi.model.dto.AppointmentResponse;
import com.eugene.hospitalapi.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping
    public AppointmentResponse create(@Valid @RequestBody AppointmentCreateRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<AppointmentResponse> getAll() {
        return service.getAll();
    }
}
