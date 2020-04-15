package com.code515.report.report_demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class User {

  @Id
  @GeneratedValue
  private Integer userId;
  private String userName;
  private String userPassword;
  private Integer orgId;
  private Integer userStatus;
  private Date createTime;

}
