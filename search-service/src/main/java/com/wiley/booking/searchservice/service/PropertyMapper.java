package com.wiley.booking.searchservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.wiley.booking.offer.Offer;
import com.wiley.booking.searchservice.service.model.Property;

import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */
@Log4j2
@Component
public class PropertyMapper {

  public Property map(final com.wiley.booking.property.Property property) {
    return Property.builder()
        .id(property.getId())
        .name(property.getName())
        .address(property.getAddress())
        .price(property.getPrice())
        .email(property.getEmail())
        .hotlineMobile(property.getHotlineMobile())
        .available(property.isAvailable())
        .fromDate(property.getFromDate())
        .toDate(property.getToDate())
        .build();
  }

  public List<Property> map(
      final List<com.wiley.booking.property.Property> properties) {

    List<Property> mappedProps =
        properties.stream()
            .map(
                property ->
                    Property.builder()
                        .id(property.getId())
                        .name(property.getName())
                        .address(property.getAddress())
                        .price(property.getPrice())
                        .email(property.getEmail())
                        .hotlineMobile(property.getHotlineMobile())
                        .available(property.isAvailable())
                        .fromDate(property.getFromDate())
                        .toDate(property.getToDate())
                        .build())
            .collect(Collectors.toList());
    return mappedProps;
  }

  public List<Property> calculateDiscounts(List<Property> properties, List<Offer> offers)
  {
    if(offers != null && !offers.isEmpty())
    {
      return properties;
    }
    else{
      if(properties != null && !properties.isEmpty())
      {
        properties.forEach(p -> {
          offers.stream().filter(o -> p.getId().equals(o.getPropertyId())).forEach(o -> {
            p.setDiscount(o.getDiscount());
            p.setDiscountedPrice(calculateDiscountedPrice(p.getPrice(), o.getDiscount()));
          });
        });
      }
    }

    return properties;
  }
  private double calculateDiscountedPrice(final double price, final double discountRate) {
    return (price - (price * discountRate));
  }
}
