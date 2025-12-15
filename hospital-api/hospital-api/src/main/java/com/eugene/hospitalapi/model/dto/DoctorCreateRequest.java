package com.eugene.hospitalapi.model.dto;

import jakarta.validation.constraints.NotBlank;

public record DoctorCreateRequest(
        @NotBlank(message = "name is required") String name,
        @NotBlank(message = "specialty is required") String specialty
) {}
