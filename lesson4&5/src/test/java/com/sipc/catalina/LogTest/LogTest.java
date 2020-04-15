package com.sipc.catalina.LogTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogTest {

//    private final Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test(){
//        logger.info("这是info级别的日志");
//        logger.debug("这是debug级别的日志");
//        logger.error("这是error级别的日志");

        String username = "admin";
        String password = "987654";

        log.info("info: 用户名:"+username+",密码:"+password);
        log.debug("debug: 用户名:"+username+",密码:"+password);
        log.error("error: 用户名:{},密码:{} ", username, password);

    }

}
