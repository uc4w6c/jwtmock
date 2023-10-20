package com.github.jwtmock.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jwtmock.presentation.response.TokenResponse;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithm;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenService {
  private ObjectMapper objectMapper;
  private JwtEncoder jwtEncoder;

  public TokenService(ObjectMapper objectMapper, JwtEncoder jwtEncoder) {
    this.objectMapper = objectMapper;
    this.jwtEncoder = jwtEncoder;
  }

  public Jwt generateToken(String payloadJson) {
    Map<String, Object> payload;
    try {
      payload =
          objectMapper.readValue(payloadJson, new TypeReference<Map<String, Object>>() {});
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder();
    payload.entrySet().forEach(entry -> claimsBuilder.claim(entry.getKey(), entry.getValue()));
    JwtClaimsSet claims = claimsBuilder.build();

    JwsAlgorithm jwsAlgorithm = SignatureAlgorithm.RS256;
    JwsHeader.Builder jwsHeaderBuilder = JwsHeader.with(jwsAlgorithm);
    JwsHeader jwsHeader = jwsHeaderBuilder.build();

    return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims));
  }
}
