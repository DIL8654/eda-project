package com.wiley.booking.searchservice.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import com.wiley.booking.searchservice.util.UserUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/9/2021
 */
@Log4j2
@RequiredArgsConstructor
@Component
public class AuthService {

  public boolean isPremiumUser(final HttpServletRequest request) {
    if (request.getUserPrincipal() instanceof OAuth2Authentication) {
      OAuth2Authentication oath = (OAuth2Authentication) request.getUserPrincipal();
      if (oath.getUserAuthentication().getAuthorities() != null
          && !oath.getUserAuthentication().getAuthorities().isEmpty()) {
        for (GrantedAuthority grant : oath.getUserAuthentication().getAuthorities()) {
          if (UserUtils.PREMIUM.equals(grant.getAuthority())) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
