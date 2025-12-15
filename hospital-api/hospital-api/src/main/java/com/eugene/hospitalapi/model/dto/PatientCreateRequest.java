package com.eugene.hospitalapi.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PatientCreateRequest(
        @NotBlank(message = "name is required") String name,
        @Email(message = "email must be valid")
        @NotBlank(message = "email is required") String email
) {}
