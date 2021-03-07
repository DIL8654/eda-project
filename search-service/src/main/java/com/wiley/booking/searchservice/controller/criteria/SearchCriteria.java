package com.wiley.booking.searchservice.controller.criteria;

import java.io.Serializable;

import com.wiley.booking.user.UserType;

import lombok.Data;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */
@Data
public class SearchCriteria implements Serializable {

  private String location;
  private long from;
  private long to;
  private UserType userType;
}
