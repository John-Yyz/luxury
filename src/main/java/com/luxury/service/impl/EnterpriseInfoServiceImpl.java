package com.luxury.service.impl;

import com.luxury.base.BaseQuery;
import com.luxury.mapper.EnterpriseInfoMapper;
import com.luxury.model.EnterpriseInfo;
import com.luxury.service.IEnterpriseInfoService;
import com.luxury.utils.*;
import org.springframework.beans.BeanUtils;
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
 * @date 2021/11/28 11:19
 */
@Service(value = "enterpriseInfoService")
public class EnterpriseInfoServiceImpl implements IEnterpriseInfoService {

    @Autowired(required = false)
    EnterpriseInfoMapper enterpriseInfoMapper;

    /**
     * 检查用户是否是企业用户
     *
     * @param userId
     * @return
     */
    @Override
    public JsonResult checkIsEnterprise(String userId) {
        if(StringUtils.isBlank(userId)){
            return JsonResult.error(ErrorCode.PARAM_ERROR);
        }
        Map<String,Object> params = new HashMap<>(1);
        params.put("userId",userId);
        params.put("status",1);
        EnterpriseInfo enterpriseInfo = enterpriseInfoMapper.selectBySelect(params);
        if(Objects.isNull(enterpriseInfo)){
            //说明还不是企业
            return JsonResult.error(ErrorCode.NOT_ENTERPRISE);
        }
        if(null != enterpriseInfo.getAuditStatus() && 2 == enterpriseInfo.getAuditStatus()){
            //企业认证信息有误
            return JsonResult.error(ErrorCode.ENTERPRISE_INFO_ERROR);
        }
        return JsonResult.success("是企业用户",enterpriseInfo);
    }

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
     * 根据实体对象添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult add(EnterpriseInfo entity) {
        if(Objects.isNull(entity)){
            return JsonResult.error(ErrorCode.PARAM_ERROR);
        }
        entity.setUserId(UUIDUtil.createCode().toUpperCase());
        entity.setStatus(1);
        entity.setAuditStatus(1);
        entity.setCreateDate(DateUtils.getTime());
        int flag = enterpriseInfoMapper.insertSelective(entity);
        if(flag > 0){
            return JsonResult.success();
        }else{
            return JsonResult.error(ErrorCode.FAILED);
        }
    }

    /**
     * 根据实体对象更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult update(EnterpriseInfo entity) {
        if(Objects.isNull(entity)){
            return JsonResult.error(ErrorCode.PARAM_ERROR);
        }

        Map<String,Object> params = new HashMap<>(1);
        params.put("userId",entity.getUserId());
        params.put("status",1);
        EnterpriseInfo enterpriseInfo = enterpriseInfoMapper.selectBySelect(params);
        int flag = 0;
        if(Objects.isNull(enterpriseInfo)){
            entity.setUserId(UUIDUtil.createCode().toUpperCase());
            entity.setStatus(1);
            entity.setAuditStatus(1);
            entity.setCreateDate(DateUtils.getTime());
            enterpriseInfoMapper.insertSelective(entity);
            return JsonResult.success();
        }else{
            BeanUtils.copyProperties(entity,enterpriseInfo);
            enterpriseInfo.setStatus(1);
            enterpriseInfo.setAuditStatus(1);
            flag = enterpriseInfoMapper.updateByPrimaryKeySelective(enterpriseInfo);
            if(flag > 0){
                return JsonResult.success();
            }else{
                return JsonResult.error(ErrorCode.FAILED);
            }
        }

    }

    /**
     * 根据实体对象添加、编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(EnterpriseInfo entity) {
        return null;
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(EnterpriseInfo entity) {
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
