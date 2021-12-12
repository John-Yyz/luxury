package com.luxury.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luxury.request.OuthSourceEnt;
import com.luxury.request.ReqInfoEnt;
import com.luxury.utils.ErrorCode;
import com.luxury.utils.JsonResult;
import com.luxury.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/4 2:07
 */
@Component
public class JwtFilter extends GenericFilter{
    static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);

    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        try {

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            HttpServletRequest requestWrapper = null;
            if (request instanceof HttpServletRequest) {
                requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
            }

            String reqUrl = requestWrapper.getRequestURI();
            if(reqUrl.contains("/null")){
                reqUrl.replace("/null","");
            }

            if (reqUrl.indexOf("/outh/") == 0) {

                Integer platype = StringUtils.isBlank(requestWrapper.getHeader("platype")) ? null
                        : Integer.valueOf(requestWrapper.getHeader("platype"));

                String uid = StringUtils.isBlank(requestWrapper.getHeader("uid")) ? null
                        : String.valueOf(requestWrapper.getHeader("uid"));

                OuthSourceEnt outhSourceEnt = new OuthSourceEnt();
                outhSourceEnt.setPlatype(platype);
                outhSourceEnt.setUid(uid);
                requestWrapper.setAttribute("outhSourceEnt", outhSourceEnt);

                chain.doFilter(requestWrapper, res);
                return;
            } else if (reqUrl.indexOf("/api/") == 0) {
                // 客户端将token封装在请求头中，格式为（Bearer后加空格）：Authorization：Bearer +token
                final String jsonWebToken = request.getHeader("Authorization");

                // 签名信息
                final String sign = request.getHeader("sign"); // 签名

                // 时间戳(单位：秒)
                final String ts = request.getHeader("ts");

                // 请求参数
                JSONObject jsonObject = getPostBody(requestWrapper);

                /** 统一采用权限认证信息 */
                ReqInfoEnt reqInfoEnt = OuthFilter.getInstance().checkOuth(jsonWebToken, ts, sign, jsonObject);
                int reqSta = reqInfoEnt.getPlatype();
                switch (reqSta) {
                    case 404:
                        sendJsonObject(response, JsonResult.error(ErrorCode.TOKEN_INVALID.getCode(),ErrorCode.TOKEN_INVALID.getMsg()));
                        return;
                    case 500:
                        sendJsonObject(response, JsonResult.error(ErrorCode.TOKEN_INVALID.getCode(),ErrorCode.TOKEN_INVALID.getMsg()));
                        return;
                    case 502:
                        sendJsonObject(response, JsonResult.error(ErrorCode.SIGN_ERROR.getCode(), ErrorCode.SIGN_ERROR.getMsg()));
                        return;
                    default:
                        break;
                }

                // 请求IP
                String reqIp = getRemoteIP(requestWrapper);
                reqInfoEnt.setReqIp(reqIp);

                //员工-单点登录屏蔽
                if(reqUrl.contains("getEmployInfoByTokenForAndroid")){
                    reqInfoEnt.setJwtToken(jsonWebToken);
                }
                requestWrapper.setAttribute("reqInfoEnt", reqInfoEnt);
                chain.doFilter(requestWrapper, res);
            } else if (reqUrl.contains("swagger-ui")) {
                chain.doFilter(req, res);
            } else if (reqUrl.contains("config")) {
                chain.doFilter(requestWrapper, res);
            } else if (reqUrl.contains("druid")) {
                chain.doFilter(requestWrapper, res);
            } else {
                // LOGGER.error(" 请求链接不存在,请确认！！！ " + reqUrl);
                sendJsonObject(response, JsonResult.error(ErrorCode.FAILED.getCode(), "请求链接不存在,请确认！！！"));
            }
        } catch (Exception e) {
            sendJsonObject(response, JsonResult.error(ErrorCode.FAILED.getCode(),  "验签异常"));
            return;
        }
    }

    /**
     * @Description: 获取请求参数
     * @param @param
     *            request
     * @param @return
     * @return JSONObject
     * @throws @author
     *             龚恒
     * @date 2017年9月19日
     */
    private JSONObject getPostBody(HttpServletRequest request) {
        JSONObject myJsonObject = null;
        try {
            request.setCharacterEncoding("UTF-8");
            BufferedReader bur = new BufferedReader(request.getReader());
            String temp = "";
            String body = "";
            while ((temp = bur.readLine()) != null) {
                body += temp;
            }
            myJsonObject = JSONObject.parseObject(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myJsonObject;
    }

    /**
     * @Description: 响应数据
     * @param @param
     *            response
     * @param @param
     *            object
     * @return void
     * @throws @author
     *             yuyz
     * @date 2017年9月19日
     */
    protected void sendJsonObject(HttpServletResponse response, Object object) {
        String str = JSONObject.toJSONString(object);
        Writer out = null;
        try {
            response.setContentType("text/json; charset=UTF-8");
            response.addHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题,注：ajax跨域访问时无法在header上带数据的
            out = response.getWriter();
            out.write(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return checkIP(ip);
    }

    private String checkIP(String IP) {
        String ipStr = null;
        if (IP.contains(":")) {
            int ipf = IP.lastIndexOf(":") + 1;
            ipStr = IP.substring(ipf);
        } else {
            ipStr = IP;
        }
        String[] ipString = ipStr.split("\\.");
        if (ipString.length != 4) {
            return "127.0.0.1";
        } else {
            return ipStr;
        }
    }
}
