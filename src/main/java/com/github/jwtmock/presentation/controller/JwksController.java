package com.github.jwtmock.presentation.controller;

import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${jwtmock.jwks-url}")
public class JwksController {
  private JWKSource<SecurityContext> jwkSource;

  public JwksController(JWKSource<SecurityContext> jwkSource) {
    this.jwkSource = jwkSource;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public String index() {
    JWKSelector jwkSelector = new JWKSelector(new JWKMatcher.Builder().build());

    JWKSet jwkSet;
    try {
      jwkSet = new JWKSet(this.jwkSource.get(jwkSelector, null));
    } catch (Exception ex) {
      throw new IllegalStateException("Failed to select the JWK(s) -> " + ex.getMessage(), ex);
    }
    return jwkSet.toString();
  }
}
