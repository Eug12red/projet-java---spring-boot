package com.eugene.hospitalapi.model.dto;

public record PatientResponse(
        Long id,
        String name,
        String email
) {}
