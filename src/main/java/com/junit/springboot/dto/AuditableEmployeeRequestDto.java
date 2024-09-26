package com.junit.springboot.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record AuditableEmployeeRequestDto(@NotEmpty String name, @NotEmpty String id, @NotEmpty String dob){}
