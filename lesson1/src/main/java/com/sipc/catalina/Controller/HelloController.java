package com.sipc.catalina.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController //Controller + ResponseBody = RestController
public class HelloController {

    @Autowired
    private TodoListConfig todoListConfig;

    @Value("${todo.date}")
    private String date;

    @GetMapping({"/welcome", "/hi"})
    public String welcome() {
        return todoListConfig.getThing();
    }

    @PostMapping("/say")
    public String say(@RequestParam(value = "id", required = false, defaultValue = "0") Integer studentId) {
        return "学生的id是" + studentId;
    }
}