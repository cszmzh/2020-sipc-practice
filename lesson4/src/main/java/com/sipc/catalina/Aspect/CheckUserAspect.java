package com.sipc.catalina.Aspect;

import com.sipc.catalina.service.CheckUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckUserAspect {

    @Autowired
    private CheckUserService checkUserService;

    @Pointcut("execution(* com.sipc.catalina.service.*.*(..))")
        public void checkAdmin(){

    }



    /**
     *  标记注解方式拦截
     * */
//    @Pointcut("@annotation(com.sipc.catalina.Annotation.adminOnly)")
//    public void checkAdmin(){
//
//    }

//    @Before("checkAdmin()")
//    public void before(JoinPoint joinPoint) {
//        System.out.println("-------【前置通知】-------" + joinPoint);
//    }

//    @After("checkAdmin()")
//    public void after(JoinPoint joinPoint) {
//        System.out.println("-------【后置通知】-------" + joinPoint);
//    }

//    @AfterReturning(value = "checkAdmin()", returning = "userId")
//    public void afterReturning(Object userId) {
//        System.out.println("-------【返回通知】-------" + userId);
//    }

//    @AfterThrowing(value = "checkAdmin()", throwing = "e")
//    public void afterThrowing(Throwable e) {
//        System.out.println("-------【异常通知】-------" + e.getMessage());
//    }

    @Around(value = "checkAdmin()")
    public Object aroud(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("-------【环绕通知前】-------" );
        Object obj = joinPoint.proceed();   //执行目标方法
        System.out.println("-------【环绕通知后】--------");
        return obj;
    }

}
