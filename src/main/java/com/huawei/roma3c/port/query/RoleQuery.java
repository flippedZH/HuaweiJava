package com.huawei.roma3c.port.query;

import com.huawei.roma3c.port.base.BaseQuery;

/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
public class RoleQuery extends BaseQuery {

    private String roleName; // 角色名称

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
