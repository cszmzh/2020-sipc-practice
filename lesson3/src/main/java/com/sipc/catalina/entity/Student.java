package com.sipc.catalina.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Integer id; //学号
    @NotNull(message = "姓名为空")
    private String name; //姓名
    @NotNull(message = "性别为空")
    private String sex; //性别
    @NotNull(message = "学费为空")
    private BigDecimal fee; //学费

    public Student() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
