package com.huawei.roma3c.port.service;


import com.huawei.roma3c.port.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Description: no desc
 * User: zh
 * Date: 2022/8/17
 * Time: 23:12
 */


@Service
public class UserService {

    public  void func(String userName,String passWord){
        /**
        *1、校验用户名 密码
        *   为空 -->结束
        *   不为空
        *      -->继续
        *       2、从数据中查询用户名
        *            无结果--》结束
        *            有结果
        *               --》
        *               比对密码
        *                   不正确
        *                   正确
        *                      返回应对的信息
        */
        //运行时异常==程序直接终止
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名为空");
        AssertUtil.isTrue(StringUtils.isBlank(userName),"密码为空");


    }



}
