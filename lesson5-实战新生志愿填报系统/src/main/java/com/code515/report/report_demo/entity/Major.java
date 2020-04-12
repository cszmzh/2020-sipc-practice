package com.code515.report.report_demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Major {

  @Id
  private Integer majorId;
  private String majorName;

}
