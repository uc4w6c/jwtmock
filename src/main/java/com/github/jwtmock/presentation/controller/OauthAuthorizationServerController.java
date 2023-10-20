package com.github.jwtmock.presentation.controller;

import com.github.jwtmock.presentation.helper.UriHelper;
import com.github.jwtmock.presentation.response.OauthAuthorizationServerResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${jwtmock.oauth-authorization-server-url}")
public class OauthAuthorizationServerController {
  private UriHelper uriHelper;

  public OauthAuthorizationServerController(UriHelper uriHelper) {
    this.uriHelper = uriHelper;
  }

  @GetMapping
  public OauthAuthorizationServerResponse index(HttpServletRequest request) {
    String issuer = uriHelper.getContextPath(request);
    return new OauthAuthorizationServerResponse(issuer, issuer + "/oauth2/jwks");
  }
}
