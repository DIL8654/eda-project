package com.wiley.booking.searchservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author Dilanka
 * @create at 3/8/2021
 */

@Data
@Configuration
public class EnvConfiguration {

  @Value(value = "${spring.kafka.consumer.group-id}")
  private String groupId;

  @Value(value = "${app.booking.createtopic}")
  private String createbooking;

  @Value(value = "${spring.kafka.consumer.bootstrap-servers}")
  private String bootrstapAddresses;
}
