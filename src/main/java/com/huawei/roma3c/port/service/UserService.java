package com.huawei.roma3c.port.service;


import com.huawei.roma3c.port.dao.UserMapper;
import com.huawei.roma3c.port.utils.AssertUtil;
import com.huawei.roma3c.port.utils.Md5Util;
import com.huawei.roma3c.port.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: no desc
 * User: zh
 * Date: 2022/8/17
 * Time: 23:12
 */




@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;//?

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

        checkPasswordParams(userName,passWord);
        User user=userMapper.queryUserByUserName(userName);
        AssertUtil.isTrue(null==user,"用户不存在");
        AssertUtil.isTrue((user.getUserPwd()).equals(Md5Util.encode(passWord)),"密码错误");
    }


    private void checkPasswordParams(String userName,  String passWord) {
        //运行时异常==程序直接终止
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名为空");
        AssertUtil.isTrue(StringUtils.isBlank(passWord),"密码为空");
    }

}
