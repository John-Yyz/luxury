package com.luxury.service;

import com.luxury.base.IBaseService;
import com.luxury.model.EnterpriseInfo;
import com.luxury.utils.JsonResult;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/28 11:10
 */
public interface IEnterpriseInfoService extends IBaseService<EnterpriseInfo> {

    /**
     * 检查用户是否是企业用户
     * @param userId
     * @return
     */
    JsonResult checkIsEnterprise(String userId);
}
