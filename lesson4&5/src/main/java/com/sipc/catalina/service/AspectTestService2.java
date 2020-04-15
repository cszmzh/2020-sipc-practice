package com.sipc.catalina.service;

import org.springframework.stereotype.Service;

@Service
public class AspectTestService2 {

    public void insert() throws Exception {
        System.out.println("AspectTestService2增加记录成功!");
    }

    public void delete() throws Exception {
        System.out.println("AspectTestService2删除记录成功!");
    }

    public void update() throws Exception {
        System.out.println("AspectTestService2更新纪录成功！");
    }

    public void find(){
        System.out.println("AspectTestService2查询成功！");
    }
}
