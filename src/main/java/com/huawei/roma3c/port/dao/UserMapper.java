package com.huawei.roma3c.port.dao;

import com.huawei.roma3c.port.base.BaseMapper;
import com.huawei.roma3c.port.vo.User;

import java.util.List;
import java.util.Map;

/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
public interface UserMapper extends BaseMapper<User, Integer> {


    User queryUserByUserName(String userName);

    // 通过用户名查询用户记录，返回用户对象
    public User queryUserByName(String userName);

    // 查询所有的销售人员
    List<Map<String, Object>> queryAllSales();

    // 查询所有的客户经理
    List<Map<String, Object>> queryAllCustomerManagers();
}