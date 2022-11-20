package com.luxury.controller;

import com.luxury.config.URLConstants;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/26 23:07
 */
@Api(description = "【APP】用户信息管理", tags = "EnterpriseUserController", basePath = "/enterpriseUser")
@RestController
@RequestMapping(URLConstants.USER_INFO_CONTROLLER)
@Slf4j
public class SystemUserController {
}
