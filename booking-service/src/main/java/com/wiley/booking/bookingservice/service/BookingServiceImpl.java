package com.wiley.booking.bookingservice.service;

import java.time.Instant;
import java.util.function.Function;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;
import com.wiley.booking.booking.Booking;
import com.wiley.booking.booking.BookingStatus;
import com.wiley.booking.bookingservice.exception.ReservationUpdateException;
import com.wiley.booking.bookingservice.repository.BookingRepository;
;
import com.wiley.booking.payment.PaymentStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

  private final BookingRepository bookingRepository;
  final MongoOperations mongoOperations;

  @Override
  public Booking createBooking(Booking booking) {

    booking.setBookingStatus(BookingStatus.BOOKING_CREATED);
    booking.setCreatedAt(Instant.now());
    booking.setModifiedAt(Instant.now());
    booking.setPaymentRefId("custom");
    Booking saved = bookingRepository.save(booking);

    return saved;
  }

  @Override
  public String updatePaymentRef(final String bookingRef, final String paymentRef, final
      PaymentStatus paymentStatus) {

    final Update update = new Update();
    update.set("paymentRefId", paymentRef);
    update.set("modifiedAt", Instant.now());

    if(PaymentStatus.PAYMENT_SUCCESS.equals(paymentStatus))
    {
      update.set("bookingStatus", BookingStatus.BOOKING_CONFIRMED);
    } else if(PaymentStatus.PAYMENT_FAILED.equals(paymentStatus)) {

      update.set("bookingStatus", BookingStatus.BOOKING_PAYMENT_FAILED);
    }
    final Query query = new Query();
    query.addCriteria(Criteria.where("reservationRef").is(bookingRef));


    final UpdateResult updateResult = mongoOperations.updateFirst(query, update, Booking.class);

    if (updateResult.getMatchedCount() > 0) {
      log.info(
          "Reservation paymentRefId updated successfully. (updated = {})",
          update.getUpdateObject());
    } else {
      log.error("Reservation paymentRefId update failed. (updated = {})", update.getUpdateObject());

      throw new ReservationUpdateException(
          "Unable to update Order details for (Order Id=" + bookingRef + ")");
    }
    return bookingRef;
  }
}
