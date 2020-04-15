package com.sipc.catalina.service;

import com.sipc.catalina.holder.CurrentUserHolder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void insert() throws Exception {
        CurrentUserHolder.set("abcde");
        studentService.delete();
    }
}