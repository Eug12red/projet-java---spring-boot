package com.eugene.hospitalapi.model.dto;

public record DoctorResponse(
        Long id,
        String name,
        String specialty
) {}
