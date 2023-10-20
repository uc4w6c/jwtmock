package com.github.jwtmock.presentation.validator;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class JsonStringValidator implements ConstraintValidator<JsonString, String> {
  private ObjectMapper objectMapper;

  public JsonStringValidator(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public boolean isValid(String string, ConstraintValidatorContext context) {
    try {
      objectMapper.readTree(string);
    } catch (JacksonException ex) {
      return false;
    }
    return true;
  }
}
