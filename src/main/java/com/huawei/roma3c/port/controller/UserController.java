package com.huawei.roma3c.port.controller;


import com.huawei.roma3c.port.base.BaseController;
import com.huawei.roma3c.port.base.ResultInfo;
import com.huawei.roma3c.port.exception.ParamsException;
import com.huawei.roma3c.port.model.UserModel;
import com.huawei.roma3c.port.service.UserService;
import com.huawei.roma3c.port.utils.CookieUtil;
import com.huawei.roma3c.port.utils.LoginUserUtil;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * Description: no desc
 * User: zh
 * Date: 2022/8/18
 * Time: 23:22
 */


@Controller

public class UserController  extends BaseController {

    @Autowired
    UserService userService;

    @RequestMapping("/user/login")
    @ResponseBody
    public ResultInfo login (String userName,String userPwd){
        ResultInfo resultInfo=new ResultInfo();
        /**
        * 登陆成功后：
         * 1、将用户信息保存在服务端：session (重启后失效)
        * 2、将用户信息保存在客户端：cookie
        *
        * */
        UserModel userModel = userService.loginService(userName,userPwd);
        resultInfo.setResult(userModel);
        return resultInfo;
    }

    @RequestMapping("/user/updatePassword")
    @ResponseBody
    public ResultInfo updatePassword (HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        ResultInfo resultInfo=new ResultInfo();
        userService.updateUserPassword(userId,oldPassword, newPassword,confirmPassword);
        return resultInfo;
    }

    @RequestMapping("/user/toPasswordPage")
    public String toUpdatePasswordPage(){
        return  "user/password";
    }
}
