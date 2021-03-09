package com.wiley.booking.searchservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.wiley.booking.order.Order;

/**
 * @author Dilanka
 * @create at 3/9/2021
 */
public interface OrderRepository extends CrudRepository<Order, String> {

}
