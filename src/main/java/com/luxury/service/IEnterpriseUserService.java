package com.luxury.service;

import com.luxury.base.IBaseService;
import com.luxury.model.EnterpriseUser;
import com.luxury.utils.JsonResult;
import org.springframework.stereotype.Service;

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
     * @param enterpriseUser
     * @return
     */
    JsonResult checkUserIsExist(EnterpriseUser enterpriseUser);

}
