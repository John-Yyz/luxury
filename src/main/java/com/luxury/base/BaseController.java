package com.luxury.base;

import com.alibaba.fastjson.JSONObject;
import com.luxury.Domain.HttpResponseFilter;
import com.luxury.request.OuthSourceEnt;
import com.luxury.request.ReqInfoEnt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * 基类控制器
 */
public class BaseController {

    /**
     * 构造函数
     */
    public BaseController() {

    }

    static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    private static final String CONTENT_TYPE_JSON = "text/json; charset=UTF-8";

    /**
     * 响应数据给前端，不过滤任何字段
     * @param response 响应流
     * @param data 返回的对象
     */
    protected void sendJsonObject(HttpServletResponse response, Object data) {
        sendJsonData(response, JSONObject.toJSONString(data, HttpResponseFilter.DEFAULT_SERIALIZER_FEATURES));
    }

    /**
     * 响应数据给前端，过滤掉创建者与更新者 6个字段
     * @param response 响应流
     * @param data 返回的对象
     */
    protected void sendJsonFilterCreater(HttpServletResponse response, Object data){
        HttpResponseFilter filter = new HttpResponseFilter(true);
        sendJsonFilters(response,data,filter);
    }
    /**
     * 响应数据给前端，并且指定过滤器进行json数据构造
     * @param response 响应流
     * @param data 返回的对象
     * @param filter 过滤器
     */
    protected void sendJsonFilters(HttpServletResponse response, Object data, HttpResponseFilter filter){
        if(filter == null){
            sendJsonObject(response, data);
        }else{
            String jsonStr = filter.appendFilter(data);
            sendJsonData(response,jsonStr);
        }
    }

    /** 响应数据给前端
     * @param response 响应流
     * @param str 已经编码的json字符串数据
     */
    protected void sendJsonData(HttpServletResponse response, String str) {
        Writer out = null;
        try {
            response.setContentType(CONTENT_TYPE_JSON);
            response.addHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题,注：ajax跨域访问时无法在header上带数据的
            out = response.getWriter();
            out.write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: 获取签名的平台及用户信息(必填)
     * @param @param request
     * @param @return
     * @param @throws UnsupportedEncodingException
     * @return ReqInfoEnt
     * @throws
     * @author 龚恒
     * @date 2018年3月3日
     */
    public ReqInfoEnt getReqInfoEnt(HttpServletRequest request) throws UnsupportedEncodingException{
        ReqInfoEnt reqInfoEnt = null;
        try {
            Object object = request.getAttribute("reqInfoEnt");
            if(object != null){
                reqInfoEnt = (ReqInfoEnt) object;
            }
        } catch (Exception e) {
            logger.error("获取签名信息中用户信息异常---》 ", e);
        }
        return reqInfoEnt;
    }

    /**
     * @Description: 获取非签名的平台及用户信息(非必填)
     * @param @param request
     * @param @return
     * @param @throws UnsupportedEncodingException
     * @return OuthSourceEnt
     * @throws
     * @author 龚恒
     * @date 2018年3月3日
     */
    public OuthSourceEnt getOuthSourceEnt(HttpServletRequest request) throws UnsupportedEncodingException {
        OuthSourceEnt outhSourceEnt  = null;
        try {
            Object object = request.getAttribute("outhSourceEnt");
            if(object != null){
                outhSourceEnt = (OuthSourceEnt) object;
            }
        } catch (Exception e) {
            logger.error("获取非签名信息中用户信息异常---》 ",e);
        }
        return outhSourceEnt;
    }

}
