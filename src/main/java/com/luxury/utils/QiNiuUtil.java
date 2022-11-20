package com.luxury.utils;

import com.google.gson.Gson;
import com.luxury.config.QiNiuConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 描述：七牛云 后台上传
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/18 17:57
 */
@Slf4j
public class QiNiuUtil {

    /**
     * 上传本地文件
     * @param localFilePath 本地文件完整路径
     * @param key 文件云端存储的名称
     * @param override 是否覆盖同名同位置文件
     * @return
     */
    public static boolean upload(String localFilePath, String key, boolean override){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(QiNiuConfig.getInstance().getZone());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        Auth auth = Auth.create(QiNiuConfig.getInstance().getAccessKey(), QiNiuConfig.getInstance().getSecretKey());
        String upToken;
        if(override){
            //覆盖上传凭证
            upToken = auth.uploadToken(QiNiuConfig.getInstance().getBucket(), key);
        }else{
            upToken = auth.uploadToken(QiNiuConfig.getInstance().getBucket());
        }
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return true;
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                log.error(r.bodyString());
            } catch (QiniuException ex2) {
                return false;
            }
            return false;
        }
    }

    /**
     * 获取文件访问地址
     * @param fileName 文件云端存储的名称
     * @return
     */
    public static String fileUrl(String fileName){
        try {
            String encodedFileName = URLEncoder.encode(fileName, "utf-8");
            String publicUrl = String.format("%s/%s", QiNiuConfig.getInstance().getDomainOfBucket(), encodedFileName);
            Auth auth = getAuth();
            long expireInSeconds = QiNiuConfig.getInstance().getExpireInSeconds();
            if(-1 == expireInSeconds){
                return auth.privateDownloadUrl(publicUrl);
            }
            return auth.privateDownloadUrl(publicUrl, expireInSeconds);
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return null;
    }

    /**
     * 上传MultipartFile
     * @param file
     * @param key
     * @param override
     * @return
     * @throws IOException
     */
    public static boolean uploadMultipartFile(MultipartFile file, String key, boolean override) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(QiNiuConfig.getInstance().getZone());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //把文件转化为字节数组
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = file.getInputStream();
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = is.read(b)) != -1){
                bos.write(b, 0, len);
            }
            byte[] uploadBytes= bos.toByteArray();

            Auth auth = getAuth();
            String upToken;
            if(override){
                //覆盖上传凭证
                upToken = auth.uploadToken(QiNiuConfig.getInstance().getBucket(), key);
            }else{
                upToken = auth.uploadToken(QiNiuConfig.getInstance().getBucket());
            }
            //默认上传接口回复对象
            DefaultPutRet putRet;
            //进行上传操作，传入文件的字节数组，文件名，上传空间，得到回复对象
            Response response = uploadManager.put(uploadBytes, key, upToken);
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return true;
        }catch (QiniuException e){
            log.error(e.getMessage());
            return false;
        }catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
    }
    /**
     * 上传输入流
     * @param in  输入流
     * @param key  文件云端存储的名称
     * @return
     */
    public static boolean upload(InputStream in,String key){
        try {
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(QiNiuConfig.getInstance().getZone());
            //...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);
            Auth auth = getAuth();
            //获取token
            String token = auth.uploadToken(QiNiuConfig.getInstance().getBucket());
            //上传输入流
            Response response = uploadManager.put(in , key, token, null,null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return true;
        }catch (Exception e) {
            throw new RuntimeException("同步失败，获取第三方url失败");
        }
    }

    /**
     * 获取凭证
     * @return
     */
    public static Auth getAuth(){
        Auth auth = Auth.create(QiNiuConfig.getInstance().getAccessKey(), QiNiuConfig.getInstance().getSecretKey());
        return auth;
    }

    /**
     * 读取网络文件
     * @param path
     * @return
     */
    public static InputStream getFileInputStream(String path) {
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            return conn.getInputStream();
        } catch (Exception e) {
            throw new RuntimeException("获取第三方url失败");
        }
    }

    /**
     * 生成随机文件名
     * @return
     */
    public static String generateRandomFilename(){
        String RandomFilename = "";
        //生成随机数
        Random rand = new Random();
        int random = rand.nextInt();
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = simpleDateFormat.format(date);
        RandomFilename = fileName + String.valueOf(random > 0 ? random : ( -1) * random);
        return RandomFilename;
    }
}
