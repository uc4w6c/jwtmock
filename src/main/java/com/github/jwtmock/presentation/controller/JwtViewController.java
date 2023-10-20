package com.github.jwtmock.presentation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jwtmock.config.InstantService;
import com.github.jwtmock.config.JwkMockConfigurationProperties;
import com.github.jwtmock.domain.service.DefaultPayloadService;
import com.github.jwtmock.presentation.helper.UriHelper;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class JwtViewController {
  private DefaultPayloadService defaultPayloadService;
  private UriHelper uriHelper;

  public JwtViewController(
      DefaultPayloadService defaultPayloadService,
      UriHelper uriHelper) {
    this.defaultPayloadService = defaultPayloadService;
    this.uriHelper = uriHelper;
  }

  @GetMapping
  public String index(Model model, HttpServletRequest request) {
    String defaultPayload = defaultPayloadService.generate(uriHelper.getContextPath(request));

    model.addAttribute("defaultJwt", defaultPayload);
    return "jwt";
  }
}
