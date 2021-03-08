package com.wiley.booking.searchservice.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.wiley.booking.searchservice.configuration.EnvConfiguration;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */

@Configuration
@EnableTransactionManagement
public class KafkaTopicConfig {

    @Autowired
    private EnvConfiguration envConfiguration;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic(envConfiguration.getCreatebooking(), 1, (short) 1);
    }

    public String getCreatebooking()
    {
        return envConfiguration.getCreatebooking();
    }
}
