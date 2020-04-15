package com.code515.report.report_demo.Service.Impl;

import com.code515.report.report_demo.Repository.StudentRepository;
import com.code515.report.report_demo.Service.StudentService;
import com.code515.report.report_demo.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;


    @Override
    public Student save(Student student) {
        return repository.save(student);
    }
}
