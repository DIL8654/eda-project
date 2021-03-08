package com.wiley.auth.authserver.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
@Configuration
@EnableEurekaClient
public class AuthServerConfiguration extends WebSecurityConfigurerAdapter
    implements AuthorizationServerConfigurer {

  PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Autowired AuthenticationManager authenticationManager;

  @Override
  public void configure(final AuthorizationServerSecurityConfigurer securityConfigurer)
      throws Exception {

    securityConfigurer.checkTokenAccess("permitAll()");
  }

  @Override
  public void configure(final ClientDetailsServiceConfigurer clientConfigurer) throws Exception {

    clientConfigurer
        .inMemory()
        .withClient("web")
        .secret(passwordEncoder.encode("webpass"))
        .scopes("READ", "WRITE")
        .authorizedGrantTypes("password", "authorization_code","client_credentials");
  }

  @Override
  public void configure(final AuthorizationServerEndpointsConfigurer endpointsConfigurer)
      throws Exception {

    endpointsConfigurer.authenticationManager(authenticationManager);
  }
}
