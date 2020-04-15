package com.sipc.catalina.service;

import org.springframework.stereotype.Service;

@Service
public class AspectTestService1 {

    public void insert() throws Exception {
        System.out.println("AspectTestService1增加记录成功!");
    }

    public void delete() throws Exception {
        System.out.println("AspectTestService1删除纪录成功！");
    }

    public void update() throws Exception {
        System.out.println("AspectTestService1更新纪录成功！");
    }

    public void find(){
        System.out.println("AspectTestService1查询成功！");
    }

}
