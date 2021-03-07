package com.wiley.booking.searchservice.util;

import com.fasterxml.uuid.Generators;

/**
 * @author Dilanka
 * @create at 3/7/2021
 */
public final class ReservationRefGenerator {

  public static String generateReservationRef()
  {
    return Generators.timeBasedGenerator().generate().toString();
  }

}
