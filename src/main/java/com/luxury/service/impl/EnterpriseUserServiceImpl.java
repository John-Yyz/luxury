package com.luxury.service.impl;

import com.luxury.base.BaseQuery;
import com.luxury.mapper.EnterpriseUserMapper;
import com.luxury.model.EnterpriseUser;
import com.luxury.service.IEnterpriseUserService;
import com.luxury.utils.JsonResult;
import com.luxury.utils.StringUtils;
import com.luxury.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/25 2:50
 */
@Service(value = "enterpriseUserService")
public class EnterpriseUserServiceImpl implements IEnterpriseUserService {

    Logger logger = LoggerFactory.getLogger(EnterpriseUserServiceImpl.class);

    @Autowired(required = false)
    EnterpriseUserMapper enterpriseUserMapper;

    /**
     * 根据查询条件获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        return null;
    }

    /**
     * 根据ID获取记录信息
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Map<String, Object> info(Integer id) {
        return null;
    }

    /**
     * 根据ID获取记录信息
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        return null;
    }

    /**
     * 检查用户是否存在
     *
     * @param enterpriseUser
     * @return
     */
    @Override
    public JsonResult checkUserIsExist(EnterpriseUser enterpriseUser) {
        if(Objects.isNull(enterpriseUser)){
            logger.info("参数错误");
            return JsonResult.error("参数错误");
        }
        if(StringUtils.isBlank(enterpriseUser.getUnionid()) && StringUtils.isBlank(enterpriseUser.getUserMobile())){
            logger.info("参数错误");
            return JsonResult.error("参数错误");
        }
        Map<String,Object> params = new HashMap<>(1);
        if(StringUtils.isNotBlank(enterpriseUser.getUnionid())){
            //通过unionId获取用户信息
            params.put("unionid",enterpriseUser.getUnionid());
            EnterpriseUser upEnterpriseUser = enterpriseUserMapper.selectByParams(params);
            if(Objects.isNull(upEnterpriseUser)){
                //通过手机号去获取用户信息，如果不存在，是真的不存在该用户
                params.put("userMobile",enterpriseUser.getUserMobile());
                upEnterpriseUser = enterpriseUserMapper.selectByParams(params);
                if(Objects.isNull(upEnterpriseUser)){
                    //前端调用插入接口
                    return JsonResult.error(10001,"用户不存在");
                }else{
                    //这个前端要调用修改接口
                    return JsonResult.success("用户已存在");
                }

            }else{
                //这个前端要调用修改接口
                return JsonResult.success("用户已存在");
            }

        }else{
            params.put("userMobile",enterpriseUser.getUserMobile());
            EnterpriseUser upEnterpriseUser = enterpriseUserMapper.selectByParams(params);
            if(Objects.isNull(upEnterpriseUser)){
                //前端调用插入接口
                return JsonResult.error(10001,"用户不存在");
            }else{
                //这个前端要调用修改接口
                return JsonResult.success("用户已存在");
            }
        }
    }

    /**
     * 根据实体对象添加记录
     *
     * @param enterpriseUser 实体对象
     * @return
     */
    @Override
    public JsonResult add(EnterpriseUser enterpriseUser) {
        if(Objects.isNull(enterpriseUser)){
            logger.info("参数错误");
            return JsonResult.error("参数错误");
        }
        String code = UUIDUtil.createCode();
        enterpriseUser.setUserId(code);
        int flag = enterpriseUserMapper.insertSelective(enterpriseUser);
        if(flag > 0){
            return JsonResult.success("注册成功",enterpriseUser);
        }
        return JsonResult.error("注册失败");
    }

    /**
     * 根据实体对象更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult update(EnterpriseUser entity) {
        if(Objects.isNull(entity)){
            logger.info("参数错误");
            return JsonResult.error("参数错误");
        }
        int flag = enterpriseUserMapper.updateByPrimaryKeySelective(entity);
        if(flag > 0){
            return JsonResult.success("修改成功",entity);
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 根据实体对象添加、编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(EnterpriseUser entity) {
        return null;
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(EnterpriseUser entity) {
        return null;
    }

    /**
     * 根据ID删除记录
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public JsonResult deleteById(Integer id) {
        return null;
    }

    /**
     * 根据ID删除记录
     *
     * @param ids 记录ID
     * @return
     */
    @Override
    public JsonResult deleteByIds(String ids) {
        return null;
    }


}
