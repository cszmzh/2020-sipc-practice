package com.code515.report.report_demo.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Student {

  @Id
  private Integer stuId;
  private String stuName;
  private Integer stuMajor;
  private Integer stuClass;
  private String stuPhone;
  private String stuQq;
  private String stuStatus;
  private Integer orgId;
}
