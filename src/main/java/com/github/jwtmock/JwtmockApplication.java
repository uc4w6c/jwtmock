package com.github.jwtmock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class JwtmockApplication {

  public static void main(String[] args) {
    SpringApplication.run(JwtmockApplication.class, args);
  }
}
