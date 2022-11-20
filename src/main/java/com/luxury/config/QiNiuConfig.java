package com.luxury.config;

import com.qiniu.common.Zone;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/18 18:00
 */
@Slf4j
@Data
@Component
public class QiNiuConfig {

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniuyun.bucket}")
    private String bucket;

    private Zone zone;
    private String domainOfBucket;
    private long expireInSeconds;

    private static QiNiuConfig instance = new QiNiuConfig ();

    private QiNiuConfig(){
        try {
            //
            domainOfBucket = "";
            //链接过期时间，单位是秒，3600代表1小时，-1代表永不过期
            expireInSeconds = Long.parseLong("-1");
            // # [{'zone0':'华东'}, {'zone1':'华北'},{'zone2':'华南'},{'zoneNa0':'北美'},{'zoneAs0':''}]
            String zoneName = "zone0";
            if(zoneName.equals("zone0")){
                zone = Zone.zone0();
            }else if(zoneName.equals("zone1")){
                zone = Zone.zone1();
            }else if(zoneName.equals("zone2")){
                zone = Zone.zone2();
            }else if(zoneName.equals("zoneNa0")){
                zone = Zone.zoneNa0();
            }else if(zoneName.equals("zoneAs0")){
                zone = Zone.zoneAs0();
            }else{
                throw new Exception("Zone对象配置错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QiNiuConfig getInstance(){
        return instance;
    }
}
