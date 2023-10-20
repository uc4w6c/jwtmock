package com.github.jwtmock.presentation.controller;

import com.github.jwtmock.domain.service.TokenService;
import com.github.jwtmock.presentation.request.TokenRequest;
import com.github.jwtmock.presentation.response.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("token")
public class TokenController {
  private TokenService tokenService;

  public TokenController(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TokenResponse generate(@RequestBody TokenRequest jwtRequest) {
    Jwt jwt = this.tokenService.generateToken(jwtRequest.payload());
    return new TokenResponse(jwt.getTokenValue());
  }
}
