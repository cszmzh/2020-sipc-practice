package com.code515.report.report_demo.Controller;

import com.code515.report.report_demo.Service.LoginAndLogoutService;
import com.code515.report.report_demo.VO.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginAndLogoutController {

    @Autowired
    private LoginAndLogoutService loginAndLogoutService;

    @PostMapping("/login")
    public ResultVO login(@RequestParam("username")String username, @RequestParam("password")String password){
        if(loginAndLogoutService.login(username, password)){
            return new ResultVO(0,"登陆成功");
        }
        return new ResultVO(1,"登录失败");
    }

    @PostMapping("/logout")
    public ResultVO logout(@RequestParam("username")String username){
        if(loginAndLogoutService.logout(username)){
            return new ResultVO(0,"注销成功");
        }
        return new ResultVO(1,"注销失败");
    }

}
