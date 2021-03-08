package com.wiley.booking.searchservice.kafka.consumer;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.wiley.booking.searchservice.configuration.EnvConfiguration;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
@Log4j2
@Component
@Data
public class KafkaConsumer {

  @Autowired
  private EnvConfiguration envConfiguration;

  private CountDownLatch latch = new CountDownLatch(1);
  private String payload = null;

  @KafkaListener(topics = "${app.booking.createtopic}")
  public void receive(ConsumerRecord<?, ?> consumerRecord) {
    log.info("received payload='{}'", consumerRecord.toString());
    setPayload(consumerRecord.toString());
    latch.countDown();
  }
}
