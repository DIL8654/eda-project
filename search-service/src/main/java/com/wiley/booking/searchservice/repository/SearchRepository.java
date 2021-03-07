package com.wiley.booking.searchservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.wiley.booking.property.Property;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */
public interface SearchRepository extends CrudRepository<Property, String> {

}
