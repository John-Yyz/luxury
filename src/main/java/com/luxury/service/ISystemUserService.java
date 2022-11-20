package com.luxury.service;


import com.luxury.base.IBaseService;
import com.luxury.model.SystemUser;
import com.luxury.utils.JsonResult;
import org.springframework.stereotype.Service;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/26 23:03
 */
public interface ISystemUserService extends IBaseService<SystemUser> {

    JsonResult regSysUser();
}
