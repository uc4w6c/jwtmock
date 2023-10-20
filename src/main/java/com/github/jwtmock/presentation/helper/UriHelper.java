package com.github.jwtmock.presentation.helper;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UriHelper {
  public String getContextPath(HttpServletRequest request) {
    return UriComponentsBuilder.fromHttpUrl(UrlUtils.buildFullRequestUrl(request))
        .replacePath(request.getContextPath())
        .replaceQuery(null)
        .fragment(null)
        .build()
        .toUriString();
  }
}
