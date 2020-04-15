package com.code515.report.report_demo.Service.Impl;

import com.code515.report.report_demo.Repository.UserRepository;
import com.code515.report.report_demo.Service.UserService;
import com.code515.report.report_demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findByUserName(String username) {
        return repository.findByUserName(username);
    }
}
