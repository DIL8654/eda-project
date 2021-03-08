package com.wiley.booking.paymentservice.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.wiley.booking.paymentservice.configuration.EnvConfiguration;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
@Configuration
@EnableTransactionManagement
public class KafkaTopicConfig {

  @Autowired EnvConfiguration envConfiguration;

  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(
        AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, envConfiguration.getBootrstapAddresses());
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic topic1() {
    return new NewTopic(envConfiguration.getPaymenttopic(), 1, (short) 1);
  }

  public String getPaymenttopic() {
    return envConfiguration.getPaymenttopic();
  }
}
