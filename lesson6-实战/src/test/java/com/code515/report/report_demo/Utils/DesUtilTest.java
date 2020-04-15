package com.code515.report.report_demo.Utils;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class DesUtilTest {

    @Test
    public void Test(){
        String username = DesUtil.encrypt("5201314");
        System.out.println(username);
        System.out.println(DesUtil.decrypt(username));
    }
}