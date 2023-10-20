package com.github.jwtmock.config;

import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class InstantService {
  public Instant now() {
    return Instant.now();
  }
}
