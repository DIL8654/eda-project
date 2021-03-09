package com.wiley.auth.authserver.authserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
@Configuration
public class UserConfiguration extends GlobalAuthenticationConfigurerAdapter {

  PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {

    auth.inMemoryAuthentication()
        .withUser("Dilanka")
        .password(passwordEncoder.encode("dpass"))
        .roles("PREMIUM")
        .authorities("PREMIUM", "BASIC")
        .and()

        .withUser("Mahesh")
        .password(passwordEncoder.encode("mpass"))
        .roles("BASIC")
        .authorities("BASIC");
  }
}
