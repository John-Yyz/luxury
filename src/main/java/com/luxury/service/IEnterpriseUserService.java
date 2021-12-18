package com.luxury.service;

import com.luxury.base.IBaseService;
import com.luxury.model.EnterpriseUser;
import com.luxury.request.CheckWechatRegistReq;
import com.luxury.request.OuthSourceEnt;
import com.luxury.utils.JsonResult;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/25 2:49
 */
@Service
public interface IEnterpriseUserService extends IBaseService<EnterpriseUser> {

    /**
     * 检查用户是否存在
     * @param checkWechatRegistReq
     * @return
     */
    JsonResult checkUserIsExist(CheckWechatRegistReq checkWechatRegistReq, OuthSourceEnt outhSourceEnt);

    /**
     * 用戶信息
     * @param enterpriseUser
     * @return
     */
    JsonResult getUserInfo(EnterpriseUser enterpriseUser);

}
