//package com.wiley.booking.searchservice;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;
//
//import java.util.concurrent.TimeUnit;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.test.context.EmbeddedKafka;
//import org.springframework.test.annotation.DirtiesContext;
//
//import com.wiley.booking.order.Booking;
//import com.wiley.booking.order.BookingStatus;
//import com.wiley.booking.searchservice.kafka.consumer.KafkaConsumer;
//import com.wiley.booking.searchservice.kafka.producer.KafkaProducer;
//import com.wiley.booking.searchservice.util.ReservationRefGenerator;
//
///**
// * @author Dilanka
// * @create at 3/7/2021
// */
//
//
//@EnableKafka
//@SpringBootTest
//@DirtiesContext
//@EmbeddedKafka(
//    partitions = 1,
//    brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
//class EmbeddedKafkaIntegrationTest {
//
//  @Autowired
//  public KafkaTemplate<String, Booking> template;
//
//  @Autowired private KafkaConsumer consumer;
//
//  @Autowired private KafkaProducer producer;
//
//  @Value("${app.booking.createtopic}")
//  private String createbooking;
//
//
//  @Test
//  public void givenEmbeddedKafkaBroker_whenSendingtoSimpleProducer_thenMessageReceived()
//      throws Exception {
//
//    Booking booking = new Booking();
//    booking.setBookingStatus(BookingStatus.BOOKING_CREATED);
//    booking.setReservationRef(ReservationRefGenerator.generateReservationRef());
//    booking.setPaymentRefId(null);
//    booking.setPropertyId("property-id");
//    booking.setUserId("user-id");
//
//    producer.send(createbooking, booking);
//    consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
//
//    assertThat(consumer.getLatch().getCount(), equalTo(0L));
//  }
//
//
//}
