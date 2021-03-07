package com.wiley.booking.paymentservice.util;

import com.fasterxml.uuid.Generators;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
public final class PaymentRefGenerator {

  public static String generateReservationRef()
  {
    return Generators.timeBasedGenerator().generate().toString();
  }

}
