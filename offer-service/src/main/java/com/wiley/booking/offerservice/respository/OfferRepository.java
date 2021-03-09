package com.wiley.booking.offerservice.respository;


import org.springframework.data.repository.CrudRepository;

import com.wiley.booking.offer.Offer;

/**
 * @author Dilanka
 * @create at 3/8/2021
 */
public interface OfferRepository extends CrudRepository<Offer, String> {

}
