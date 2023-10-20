package com.github.jwtmock.presentation.request;

import com.github.jwtmock.presentation.validator.JsonString;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record TokenRequest(@NotBlank @JsonString String payload) {}
