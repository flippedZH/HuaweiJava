package com.huawei.roma3c.port.service;


import com.huawei.roma3c.port.base.BaseService;
import com.huawei.roma3c.port.dao.UserMapper;
import com.huawei.roma3c.port.model.UserModel;
import com.huawei.roma3c.port.utils.AssertUtil;
import com.huawei.roma3c.port.utils.Md5Util;
import com.huawei.roma3c.port.utils.UserIDBase64;
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
public class UserService  extends BaseService {

    @Autowired
    private UserMapper userMapper;//?

    public UserModel loginService(String userName, String passWord){
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
        System.out.println("新");
        System.out.println(userName);
        System.out.println(passWord);
        checkPasswordParams(userName,passWord);
        User user= userMapper.queryUserByName(userName);
        AssertUtil.isTrue(null==user,"用户不存在");
        String  p1=Md5Util.encode(passWord);
        String  p2=user.getUserPwd();
        AssertUtil.isTrue(!(user.getUserPwd()).equals(Md5Util.encode(passWord)),"密码错误");
        // 返回构建用户对象
        return buildUserInfo(user);
    }

    public void updateUserPassword(Integer userId,String oldPassword,String newPassword,String confirmPassword){

        /**
        * 1、参数校验：不能为空
         * 2、两次输入的修改密码一致
         * 3、修改密码不能与老密码相同
        * */
        checkUpdatePasswordParams(userId,oldPassword,newPassword,confirmPassword);
        User user=(User)selectByPrimaryKey(userId);
        user.setUserPwd(Md5Util.encode(newPassword));
        AssertUtil.isTrue(updateByPrimaryKeySelective(user)<1,"密码更新失败");
    }

    private void checkUpdatePasswordParams(Integer userId,String oldPassword,String newPassword,String confirmPassword){
        User user= (User)selectByPrimaryKey(userId);
        AssertUtil.isTrue((null==user||null==user.getId()),"用户未登录或用户不存在！");
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"原始密码不能为空");
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(oldPassword)),"旧密码输入不正确");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword),"确认密码不能为空");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword),"两次输入密码不一致");
        AssertUtil.isTrue(newPassword.equals(oldPassword),"新旧密码不能相同");

    }


    private void checkPasswordParams(String userName,  String passWord) {
        //运行时异常==程序直接终止
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名为空");
        AssertUtil.isTrue(StringUtils.isBlank(passWord),"密码为空");
    }

    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();
        // userModel.setUserId(user.getId());
        // 设置加密的用户ID
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

}
