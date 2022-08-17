package com.huawei.roma3c.port.controller;


import com.huawei.roma3c.port.base.BaseController;
import com.huawei.roma3c.port.service.UserService;
import com.huawei.roma3c.port.utils.LoginUserUtil;
import com.huawei.roma3c.port.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("main")
    public String main(HttpServletRequest request){
        return "main";
    }

}
