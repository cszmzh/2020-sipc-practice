package com.sipc.catalina.controller;

import com.sipc.catalina.entity.Student;
import com.sipc.catalina.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;


@RestController
@Transactional
public class TestController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students/insertDouble")
    public void insert(){

        Student student1 = new Student();
        student1.setName("好基友1");
        student1.setSex("男");
        student1.setFee(new BigDecimal(1200.01));
        studentRepository.save(student1);

        Student student2 = new Student();
        student2.setName("好基友2");
        student2.setSex("男");
        student2.setFee(new BigDecimal(12000.01));
        studentRepository.save(student2);

    }
}
