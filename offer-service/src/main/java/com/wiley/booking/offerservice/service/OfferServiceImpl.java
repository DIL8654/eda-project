package com.wiley.booking.offerservice.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.wiley.booking.offer.Offer;
import com.wiley.booking.offerservice.respository.OfferRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/8/2021
 */
@Log4j2
@RequiredArgsConstructor
@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;
  private final MongoOperations mongoOperations;

  @Override
  public Optional<Offer> getById(final String id) {
    return offerRepository.findById(id);
  }

  @Override
  public Iterable<Offer> getAll() {
    return offerRepository.findAll();
  }

  @Override
  public List<Offer> getByDateRangeAndPropertyId(
      final String propertyId, final Instant from, final Instant to) {
    Date fdate = Date.from(from);
    Date tdate = Date.from(to);

    List<Offer> properties =
        mongoOperations.find(
            Query.query(Criteria.where("from").gte(fdate))
                .addCriteria(Criteria.where("to").lte(tdate))
                .addCriteria(Criteria.where("propertyId").is(propertyId)),
            Offer.class);

    return properties;
  }

  @Override
  public List<Offer> getByDateRange(final Instant from, final Instant to) {
    Date fdate = Date.from(from);
    Date tdate = Date.from(to);

    List<Offer> properties =
        mongoOperations.find(
            Query.query(Criteria.where("from").gte(fdate))
                .addCriteria(Criteria.where("to").lte(tdate)),
            Offer.class);

    return properties;
  }

  @Override
  public List<Offer> getByDateRangeAndIds(
      final List<String> propertyIds, final Instant from, final Instant to) {
    Date fdate = Date.from(from);
    Date tdate = Date.from(to);

    List<Offer> properties =
        mongoOperations.find(
            Query.query(Criteria.where("from").gte(fdate))
                .addCriteria(Criteria.where("to").lte(tdate))
                .addCriteria(Criteria.where("propertyId").in(propertyIds)),
            Offer.class);

    return properties;
  }

  @Override
  public Offer save(final Offer offer) {
    return offerRepository.save(offer);
  }
}
