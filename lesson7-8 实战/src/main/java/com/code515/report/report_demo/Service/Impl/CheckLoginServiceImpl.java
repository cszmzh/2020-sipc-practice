package com.code515.report.report_demo.Service.Impl;

import com.code515.report.report_demo.Enums.UserStatusEnum;
import com.code515.report.report_demo.Service.CheckLoginService;
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
public class CheckLoginServiceImpl implements CheckLoginService {

    @Autowired
    private UserService userService;
//    Autowired
//    private HttpServletRequest request;

    @Override
    public boolean verification_ADMIN() {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();

        //HttpSession session = request.getSession();

        Cookie[] cookies = RequestAndResponseUtil.getRequest().getCookies();

        User user;

        for(Cookie cookie : cookies){
            if(cookie.getName().equals("username")){
                user = userService.findByUserName(DesUtil.decrypt(cookie.getValue()));
                if(user == null){
                    log.error("【验证用户失败】找不到用户：{}", DesUtil.decrypt(cookie.getValue()));
                    return false;
                }
                if(user.getUserStatus().equals(UserStatusEnum.ADMIN.getCode())){
                    log.info("【验证管理员身份成功】用户名:{}", DesUtil.decrypt(cookie.getValue()));
                    return true;
                }
            }
        }
        log.error("【验证用户失败】cookie不存在");
        return false;
    }

}
