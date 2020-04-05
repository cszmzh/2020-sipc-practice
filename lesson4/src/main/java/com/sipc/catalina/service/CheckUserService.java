package com.sipc.catalina.service;

import com.sipc.catalina.holder.CurrentUserHolder;
import org.springframework.stereotype.Service;

@Service
public class CheckUserService {

    public void check() throws Exception {
        String user = CurrentUserHolder.get();
        if(!"admin".equals(user)){
            throw new Exception("【异常】你不是管理员！");
        }
    }

}
