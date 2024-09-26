package com.junit.springboot.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record EmployeeDto(@NotEmpty String name, @NotEmpty String id, @NotEmpty String dob){}
