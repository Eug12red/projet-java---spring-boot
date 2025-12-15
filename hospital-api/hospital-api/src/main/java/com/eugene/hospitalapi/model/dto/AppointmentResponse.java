
package com.eugene.hospitalapi.model.dto;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        Long patientId,
        String patientName,
        Long doctorId,
        String doctorName,
        String doctorSpecialty,
        LocalDateTime dateTime
) {}
