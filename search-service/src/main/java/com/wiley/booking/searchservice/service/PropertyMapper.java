package com.wiley.booking.searchservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.wiley.booking.searchservice.service.model.Property;

import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */
@Log4j2
@Component
public class PropertyMapper {

  public Property map(final com.wiley.booking.property.Property property, final boolean premium) {
    return Property.builder()
        .name(property.getName())
        .address(property.getAddress())
        .price(property.getPrice())
        .discount(property.getDiscountRate())
        .email(property.getEmail())
        .hotlineMobile(property.getHotlineMobile())
        .available(property.isAvailable())
        .fromDate(property.getFromDate())
        .toDate(property.getToDate())
        .discountedPrice(
            premium
                ? calculateDiscountedPrice(property.getPrice(), property.getDiscountRate())
                : 0.00)
        .build();
  }

  public List<Property> map(
      final List<com.wiley.booking.property.Property> properties, final boolean premium) {

    List<Property> mappedProps =
        properties.stream()
            .map(
                property ->
                    Property.builder()
                        .name(property.getName())
                        .address(property.getAddress())
                        .price(property.getPrice())
                        .discount(property.getDiscountRate())
                        .email(property.getEmail())
                        .hotlineMobile(property.getHotlineMobile())
                        .available(property.isAvailable())
                        .fromDate(property.getFromDate())
                        .toDate(property.getToDate())
                        .discountedPrice(
                            premium
                                ? calculateDiscountedPrice(
                                    property.getPrice(), property.getDiscountRate())
                                : 0.00)
                        .build())
            .collect(Collectors.toList());
    return mappedProps;
  }

  private double calculateDiscountedPrice(final double price, final double discountRate) {
    return (price - (price * discountRate));
  }
}
