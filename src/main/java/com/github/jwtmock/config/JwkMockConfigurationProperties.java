package com.github.jwtmock.config;

import com.github.jwtmock.presentation.validator.JsonString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;

// @Validated
@ConfigurationProperties("jwtmock")
public record JwkMockConfigurationProperties(
    @NotBlank @JsonString String defaultPayload,
    @NotNull @Positive int defaultTokenExpirationSecond) {}
