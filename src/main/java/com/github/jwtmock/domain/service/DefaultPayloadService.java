package com.github.jwtmock.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jwtmock.config.InstantService;
import com.github.jwtmock.config.JwkMockConfigurationProperties;
import com.github.jwtmock.presentation.helper.UriHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DefaultPayloadService {
  private JwkMockConfigurationProperties jwkMockConfigurationProperties;
  private ObjectMapper objectMapper;
  private UriHelper uriHelper;
  private InstantService instantService;

  public DefaultPayloadService(JwkMockConfigurationProperties jwkMockConfigurationProperties, ObjectMapper objectMapper, UriHelper uriHelper, InstantService instantService) {
    this.jwkMockConfigurationProperties = jwkMockConfigurationProperties;
    this.objectMapper = objectMapper;
    this.uriHelper = uriHelper;
    this.instantService = instantService;
  }

  public String generate(String issuer) {
    String defaultPayloadJson = jwkMockConfigurationProperties.defaultPayload();

    Map<String, Object> defaultPayload;
    try {
      defaultPayload =
          objectMapper.readValue(defaultPayloadJson, new TypeReference<Map<String, Object>>() {});
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    defaultPayload.put("iss", issuer);
    long nowEpochSecond = instantService.now().getEpochSecond();
    defaultPayload.put("nbf", nowEpochSecond);
    defaultPayload.put("exp", nowEpochSecond + jwkMockConfigurationProperties.defaultTokenExpirationSecond());
    defaultPayload.put("iat", nowEpochSecond);

    String payload;
    try {
      payload =
          objectMapper.writeValueAsString(defaultPayload);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return payload;
  }
}
