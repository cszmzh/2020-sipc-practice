package com.sipc.catalina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository repository;

    /**
     * 1.获取学生所有信息
     */
    @GetMapping("/students")
    public List<Student> getAll(){
        return repository.findAll();
    }




}
