package com.junit.springboot.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AuditableEmployeeResponseDto(@NotEmpty String name, @NotEmpty String id, @NotEmpty String dob,String createdBy, LocalDateTime createdAt){}
