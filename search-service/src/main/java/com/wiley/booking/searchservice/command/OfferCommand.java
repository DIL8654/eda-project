package com.wiley.booking.searchservice.command;

import java.util.Collections;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.wiley.booking.offer.Offer;
import com.wiley.booking.offer.OfferRequest;

/**
 * @author Dilanka
 * @create at 3/9/2021
 */
public class OfferCommand extends HystrixCommand {

  private RestTemplate restTemplate;
  private OfferRequest offerRequest;

  public OfferCommand(RestTemplate restTemplate, OfferRequest offerRequest) {
    super(HystrixCommandGroupKey.Factory.asKey("default"));
    this.restTemplate = restTemplate;
    this.offerRequest = offerRequest;
  }

  @Override
  protected List<Offer> run() throws Exception {
    List<Offer> offers =
        (List<Offer>)
            restTemplate.postForObject("http://offer/offer/getoffers", offerRequest, Object.class);
    return offers;
  }

  @Override
  protected List<Offer> getFallback() {
    return Collections.emptyList();
  }
}
