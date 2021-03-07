package com.wiley.booking.user;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author Dilanka
 * @create at 3/6/2021
 */
@Data
@Document(collation = "USER")
public class User implements Serializable {

  private String userId;
  private UserType userType;
  private String email;
  private String mobile;

}
