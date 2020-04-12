package com.code515.report.report_demo.entity.view;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class ReportView {

  //学号
  @Id
  private Integer stuId;
  private String stuName;
  private String majorName;
  private Integer stuClass;
  private String stuPhone;
  private String stuQq;
  private String orgFirst;
  private String braFirst;
  private String orgSecond;
  private String braSecond;
  private String reasonFirst;
  private String reasonSecond;
  private Integer isDispensing;
  private String stuStatus;
  private Date updateTime;
  private Date createTime;
}
