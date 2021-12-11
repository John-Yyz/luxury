package com.luxury.service;

import com.luxury.base.IBaseService;
import com.luxury.request.JwtReq;
import com.luxury.utils.JsonResult;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/2 0:35
 */
public interface IJwtService {

    /**
     *  获取jwtToken
     * @param jwtReq
     * @return
     */
    JsonResult getJwtToken(JwtReq jwtReq);
}
