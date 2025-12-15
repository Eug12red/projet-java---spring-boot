package com.eugene.hospitalapi.model.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentCreateRequest(
        @NotNull(message = "patientId is required") Long patientId,
        @NotNull(message = "doctorId is required") Long doctorId,
        @NotNull(message = "dateTime is required") LocalDateTime dateTime
) {}
