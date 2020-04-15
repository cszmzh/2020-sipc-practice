package com.code515.report.report_demo.Service.Impl;

import com.code515.report.report_demo.Repository.MajorRepository;
import com.code515.report.report_demo.Service.MajorService;
import com.code515.report.report_demo.Entity.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorRepository repository;

    @Override
    public Major findByMajorName(String name) {
        return repository.findByMajorName(name);
    }
}
