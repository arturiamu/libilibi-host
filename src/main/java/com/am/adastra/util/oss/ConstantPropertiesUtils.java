package com.am.adastra.util.oss;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 马强
 * @Description 当项目已启动，spring接口，spring加载后，执行一个接口方法
 * @create 2022-06-21 17:49
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {

    //读取配置文件内容
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyId}")
    private String keyId;
    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;

    //定义静态公开常量
    public  static String END_POINT;
    public  static String ACCESS_KEY_ID;
    public  static String ACCESS_KEY_SECRET;
    public  static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=endpoint;
        ACCESS_KEY_ID=keyId;
        ACCESS_KEY_SECRET=keysecret;
        BUCKET_NAME=bucketname;
    }
}
