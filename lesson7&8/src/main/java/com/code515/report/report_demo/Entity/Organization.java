package com.code515.report.report_demo.Entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Organization {

  @Id
  private Integer orgId;
  private String orgName;
  private String branchName;
}
