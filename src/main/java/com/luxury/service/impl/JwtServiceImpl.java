package com.luxury.service.impl;

import com.luxury.config.DomainData;
import com.luxury.mapper.UserSourceMapper;
import com.luxury.model.UserSource;
import com.luxury.request.JwtReq;
import com.luxury.service.IJwtService;
import com.luxury.utils.ErrorCode;
import com.luxury.utils.JsonResult;
import com.luxury.utils.JwtHelper;
import com.luxury.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/2 0:41
 */
@Service(value = "jwtService")
public class JwtServiceImpl implements IJwtService {

    @Autowired(required = false)
    UserSourceMapper userSourceMapper;

    /**
     * 获取jwtToken
     *
     * @param jwtReq
     * @return
     */
    @Override
    public JsonResult getJwtToken(JwtReq jwtReq) {
        String userMobile = jwtReq.getUserMobile();
        Integer userSource = jwtReq.getUserSource();
        if(StringUtils.isBlank(userMobile) || Objects.isNull(userSource)){
            return JsonResult.error(ErrorCode.PARAM_ERROR);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userMobile", userMobile);
        params.put("userSource", userSource);
        UserSource userSourceEnt = userSourceMapper.selectByUserMobileAndSource(params);
        if(Objects.isNull(userSourceEnt)){
            return JsonResult.error(ErrorCode.USER_NOT_EXIST);
        }
        if(Objects.isNull(userSourceEnt.getUserId())){
            return JsonResult.error(ErrorCode.USER_NOT_EXIST);
        }
        String jsonWebToken = JwtHelper.createJWT(userSourceEnt.getUserId(), jwtReq.getUserSource(), jwtReq.getUserMobile(),
                DomainData.USERTTLMILLIS);
        return JsonResult.success("成功",jsonWebToken);
    }
}
