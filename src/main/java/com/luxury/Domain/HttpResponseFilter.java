package com.luxury.Domain;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/4 2:40
 */
public class HttpResponseFilter {
    public static final SerializerFeature[] DEFAULT_SERIALIZER_FEATURES = {SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect};
    private static final String[] CREATER_INFO_PROPS = {"updateBy","updateDate","updateSource","createDate","createBy","createSource"};
    private SimplePropertyPreFilter serializeFilter;
    private SerializeConfig config = new SerializeConfig();
    /**
     * @param filterCreaterInfo 是否过滤创建者信息6个字段
     */
    public HttpResponseFilter(boolean filterCreaterInfo){
        if(filterCreaterInfo){
            serializeFilter = new SimplePropertyPreFilter();
            addPropFilter(CREATER_INFO_PROPS);
        }else
            serializeFilter = new SimplePropertyPreFilter();
    }
    /**添加过滤字段
     * @param props
     * @return
     */
    public void addPropFilter(String... props){
        if(props != null){
            Set<String> excludes = serializeFilter.getExcludes();
            for(String p : props){
                excludes.add(p);
            }
        }
    }
    /**添加日期格式化
     * @param format 格式化模板，例如：yyyy-MM-dd HH:mm:ss
     */
    public void addDateTimeFormat(String format){
        config.put(Date.class, new SimpleDateFormatSerializer(format));
    }

    /**
     * 格式化java.sql.Date
     * @param format
     */
    public void addSqlDateTimeFormat(String format){
        config.put(java.sql.Date.class, new SimpleDateFormatSerializer(format));
    }

    public void addTimestampFormat(String format){
        config.put(Timestamp.class, new SimpleDateFormatSerializer(format));
    }

    /**使用过滤器，构造json字符串**/
    public String appendFilter(Object data){
        String jsonStr = JSONObject.toJSONString(data,config, serializeFilter, DEFAULT_SERIALIZER_FEATURES);
        return jsonStr;
    }
}
