package com.luxury.service;

import com.luxury.request.OuthSourceEnt;
import com.luxury.request.WeChatRegisterReq;
import com.luxury.utils.JsonResult;
import org.springframework.stereotype.Service;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/17 1:08
 */
@Service
public interface IUserInfoRegistService {

    JsonResult weChatRegister(WeChatRegisterReq req, OuthSourceEnt outhSourceEnt);
}
