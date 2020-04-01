package com.sipc.catalina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private CheckUserService checkUserService;

    public void insert() throws Exception {
        checkUserService.check();
        System.out.println("增加记录成功!");
    }

    public void delete() throws Exception {
        checkUserService.check();
        System.out.println("删除纪录成功！");
    }

    public void update() throws Exception {
        checkUserService.check();
        System.out.println("更新纪录成功！");
    }

    public void find(){
        System.out.println("查询成功！");
    }
}
