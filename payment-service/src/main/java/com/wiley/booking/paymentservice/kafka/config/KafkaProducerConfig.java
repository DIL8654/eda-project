package com.wiley.booking.paymentservice.kafka.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import com.wiley.booking.paymentservice.configuration.EnvConfiguration;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
@Configuration
public class KafkaProducerConfig {

  @Autowired EnvConfiguration envConfiguration;

  @Bean(name = "kafkaTransactionManager")
  @ConditionalOnMissingBean(KafkaTransactionManager.class)
  public KafkaTransactionManager transactionManager(ProducerFactory producerFactory) {
    return new KafkaTransactionManager(producerFactory);
  }

  @Bean
  public ProducerFactory<?, ?> producerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, envConfiguration.getBootrstapAddresses());
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    DefaultKafkaProducerFactory<Object, Object> producerFactory =
        new DefaultKafkaProducerFactory<>(configProps);

    String transactionId = UUID.randomUUID().toString();
    producerFactory.setTransactionIdPrefix(transactionId);
    return producerFactory;
  }

  @Bean
  public KafkaTemplate<?, ?> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}
