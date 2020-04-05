package com.sipc.catalina.service;

import org.springframework.stereotype.Service;

@Service
public class AspectTestService3 {

    public void insert() throws Exception {
        System.out.println("AspectTestService3增加记录成功!");
    }

    public void delete() throws Exception {
        System.out.println("AspectTestService3删除纪录成功！");
    }

    public void update() throws Exception {
        System.out.println("AspectTestService3更新纪录成功！");
    }

    public void find(){
        System.out.println("AspectTestService3查询成功！");
    }

}
