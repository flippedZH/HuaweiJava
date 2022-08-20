package com.huawei.roma3c.port.controller;

import com.huawei.roma3c.port.base.BaseController;
import com.huawei.roma3c.port.dao.UserMapper;
import com.huawei.roma3c.port.vo.User;
import com.huawei.roma3c.port.service.UserService;
import com.huawei.roma3c.port.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {


    @Resource
    UserService userService;


    @RequestMapping("main")
    public String main(HttpServletRequest request){

        //请手写cookieUtil是实现类
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = (User)userService.selectByPrimaryKey(userId);
        request.setAttribute("user",user);
        return "main";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

}
