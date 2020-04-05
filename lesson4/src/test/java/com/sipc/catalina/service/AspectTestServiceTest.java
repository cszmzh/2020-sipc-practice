package com.sipc.catalina.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AspectTestServiceTest {

    @Autowired
    private AspectTestService1 aspectTestService1;

    @Autowired
    private AspectTestService2 aspectTestService2;

    @Autowired
    private AspectTestService3 aspectTestService3;

    @Test
    void test() throws Exception {
        aspectTestService1.insert();
        aspectTestService2.insert();
        aspectTestService3.insert();

        aspectTestService1.update();
        aspectTestService2.update();
        aspectTestService3.update();

        aspectTestService1.delete();
        aspectTestService2.delete();
        aspectTestService3.delete();
    }

}