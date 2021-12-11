package com.luxury.service;

import com.luxury.utils.JsonResult;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/30 22:37
 */
public interface IEnterpriseConfigInfoService {

    /**
     *
     * @param userId 用户ID
     * @param companyId 企业ID
     * @return
     */
    JsonResult getAppraisalCost(String userId,String companyId);
}
