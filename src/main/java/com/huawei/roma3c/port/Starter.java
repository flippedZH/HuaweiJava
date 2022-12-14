/**
 * Description: no desc
 * User: zh
 * Date: 2022/8/17
 * Time: 23:01
 */
package com.huawei.roma3c.port;

import com.huawei.roma3c.port.service.UserService;
import com.huawei.roma3c.port.utils.LoginUserUtil;
import com.huawei.roma3c.port.vo.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
@SpringBootApplication
@MapperScan("com.huawei.roma3c.port.dao")
//@EnableScheduling // 启用定时任务
public class Starter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class);
    }

    /* 设置Web项目的启动入口 */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Starter.class);
    }


}
