package com.wiley.booking.searchservice.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.wiley.booking.offer.Offer;
import com.wiley.booking.offer.OfferRequest;
import com.wiley.booking.searchservice.command.CommonHysctrixCommand;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/9/2021
 */
@Log4j2
@RequiredArgsConstructor
@Component
public class ServiceFacade {

  private final RestTemplate restTemplate;

  HystrixCommand.Setter setter;

  public List<Offer> getOffers(final OfferRequest offerRequest)
      throws ExecutionException, InterruptedException {
    CommonHysctrixCommand<Object> offerCommonHysctrixCommand =
        new CommonHysctrixCommand<Object>(
            "default",
            () -> {
              return restTemplate.postForObject(
                  "http://offer/offer/getoffers", offerRequest, List.class);
            },
            () -> {
              return Collections.emptyList();
            });

    Future<Object> offerFuture = offerCommonHysctrixCommand.queue();
    return (List<Offer>) offerFuture.get();
  }

  public List<Offer> fallbackOffers(final OfferRequest offerRequest) {
    return Collections.emptyList();
  }

  @com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand(fallbackMethod = "fallbackOffers")
  public List<Offer> getOffers2(final OfferRequest offerRequest)
  {
    return restTemplate.postForObject(

        "http://offer/offer/getoffers", offerRequest, List.class);
  }
}
