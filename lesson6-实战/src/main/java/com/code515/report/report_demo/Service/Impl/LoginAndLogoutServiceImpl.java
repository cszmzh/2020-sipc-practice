package com.code515.report.report_demo.Service.Impl;

import com.code515.report.report_demo.Enums.ResultEnum;
import com.code515.report.report_demo.Exception.ReportException;
import com.code515.report.report_demo.Service.LoginAndLogoutService;
import com.code515.report.report_demo.Service.UserService;
import com.code515.report.report_demo.Utils.DesUtil;
import com.code515.report.report_demo.Utils.RequestAndResponseUtil;
import com.code515.report.report_demo.Entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
@Slf4j
public class LoginAndLogoutServiceImpl implements LoginAndLogoutService {

    @Autowired
    private UserService userService;

    @Override
    public boolean login(String username, String password) {

        User user = userService.findByUserName(username);
        if(user == null){
            log.error("【登录失败】用户不存在，用户名：{}", username);
            throw new ReportException(ResultEnum.USER_NULL);
        }
        //验证密码
        if(user.getUserPassword().equals(password)){
            //设置cookie，注意要des加密
            Cookie cookie = new Cookie("username", DesUtil.encrypt(username));
            cookie.setMaxAge(60*60*24*7);
            cookie.setPath("/");
            RequestAndResponseUtil.getResponse().addCookie(cookie);
            log.info("【登陆成功】用户名：{}",username);
            return true;
        }else{
            log.error("【登录失败】用户名：{} , 密码：{} ",username, password);
        }
        return false;
    }

    @Override
    public boolean logout(String username) {
        Cookie[] cookies = RequestAndResponseUtil.getRequest().getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("username")){
                cookie.setPath("/");    //注意要设置作用路径
                cookie.setMaxAge(0);
                RequestAndResponseUtil.getResponse().addCookie(cookie);
                log.info("【注销成功】用户名：{}", username);
                return true;
            }
        }
        log.error("【注销失败】没有找到cookie，用户名：{}",username);
        return false;
    }
}
