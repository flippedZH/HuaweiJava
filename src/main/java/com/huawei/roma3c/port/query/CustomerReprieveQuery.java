package com.huawei.roma3c.port.query;

import com.huawei.roma3c.port.base.BaseQuery;

/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
public class CustomerReprieveQuery extends BaseQuery {

    // 流失客户ID
    private Integer lossId;

    public Integer getLossId() {
        return lossId;
    }

    public void setLossId(Integer lossId) {
        this.lossId = lossId;
    }
}
