package com.wiley.booking.searchservice.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.wiley.booking.searchservice.exception.PropertyNotFoundException;
import com.wiley.booking.searchservice.repository.SearchRepository;
import com.wiley.booking.searchservice.service.model.Property;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

  private final SearchRepository searchRepository;
  private final MongoOperations mongoOperations;
  private final PropertyMapper propertyMapper;

  @Override
  public List<Property> findByLocation(final String location) {

    List<Criteria> criterias = new ArrayList<>();
    criterias.add(Criteria.where("address.city").is(location));
    criterias.add(Criteria.where("address.country").is(location));

    Criteria criteria =
        new Criteria().orOperator(criterias.toArray(new Criteria[criterias.size()]));
    Query query = new Query().addCriteria(criteria);

    final List<com.wiley.booking.property.Property> data =
        mongoOperations.find(query, com.wiley.booking.property.Property.class);
    return propertyMapper.map(data);
  }

  @Override
  public Property findById(final String id) {
    Optional<com.wiley.booking.property.Property> property = searchRepository.findById(id);
    if (property.isPresent()) {
      return propertyMapper.map(property.get());
    }
    throw new PropertyNotFoundException(" Expected property not found. property id: " + id);
  }

  @Override
  public List<Property> findByDateRange(final Instant from, final Instant to) {

    Date fdate = Date.from(from);
    Date tdate = Date.from(to);

    List<Property> properties =
        mongoOperations.find(
            Query.query(Criteria.where("fromDate").gte(fdate))
                .addCriteria(Criteria.where("toDate").lte(tdate)),
            Property.class);

    return properties;
  }

  @Override
  public List<Property> findByDateRange(
      final String location, final Instant from, final Instant to) {

    Date fdate = Date.from(from);
    Date tdate = Date.from(to);

    List<Criteria> criterias = new ArrayList<>();
    criterias.add(Criteria.where("address.city").is(location));
    criterias.add(Criteria.where("address.country").is(location));

    Criteria criteria =
        new Criteria().orOperator(criterias.toArray(new Criteria[criterias.size()]));

    Query query =
        new Query()
            .addCriteria(criteria)
            .addCriteria(Criteria.where("fromDate").gte(fdate))
            .addCriteria(Criteria.where("toDate").lte(tdate));

    List<com.wiley.booking.property.Property> properties =
        mongoOperations.find(query, com.wiley.booking.property.Property.class);

    return propertyMapper.map(properties);
  }

  @Override
  public com.wiley.booking.property.Property save(final com.wiley.booking.property.Property property) {
    return searchRepository.save(property);
  }

  @Override
  public List<String> getAllLocations() {
    Iterable<com.wiley.booking.property.Property> all = searchRepository.findAll();
    List<String> allLocations = new ArrayList<>();


    if(all != null)
    {
      Iterator it = all.iterator();
      while (it.hasNext())
      {
        String location = ((com.wiley.booking.property.Property)it.next()).getAddress().getCity().trim();
        if(!allLocations.contains(location))
        {
          allLocations.add(location);
        }

      }
      Collections.sort(allLocations);
    }
    return allLocations;
  }
}
