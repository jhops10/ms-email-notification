package com.jhops10.ms_email_notification.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jhops10.ms_email_notification.business.enums.Status;

import java.time.LocalDateTime;

public record TaskDTO(
        String id,
        String taskName,
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime taskDate,
        String userEmail,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime updatedAt,
        Status status)
{

}