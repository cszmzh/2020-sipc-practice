package com.code515.report.report_demo.Aspect;

import com.code515.report.report_demo.Enums.ResultEnum;
import com.code515.report.report_demo.Exception.ReportException;
import com.code515.report.report_demo.Service.CheckLoginService;
import com.sun.tools.javac.comp.Todo;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class VerificationAspect {

    @Autowired
    private CheckLoginService checkLoginService;

    @Pointcut("execution(* com.code515.report.report_demo.Controller.ReportController.*ADMIN(..))")
    public void checkAdmin(){

    }

    @Before("checkAdmin()")
    public void verification_ADMIN(){
        //如果没有登录 这里要抛出异常
        if(!checkLoginService.verification_ADMIN()){
            throw new ReportException(ResultEnum.LOGIN_ERROR);
        }
    }

}
