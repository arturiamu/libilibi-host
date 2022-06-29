package com.am.adastra.util.oss;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.am.adastra.util.oss.ConstantPropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author 马强
 * @Description
 * @create 2022-06-21 20:19
 */
@Slf4j
public class OssUtils {

    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        //使文件名称唯一
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //把文件按照日期进行分类
        String datePath = new DateTime().toString("yyyy/MM/dd");

        String objectName = "edu/" + datePath + uuid + file.getOriginalFilename();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            InputStream inputStream = file.getInputStream();
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, inputStream);
        } catch (OSSException oe) {
            log.warn("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.warn("Error Message:{}", oe.getErrorMessage());
            log.warn("Error Code:{}", oe.getErrorCode());
            log.warn("Request ID:{}", oe.getRequestId());
            log.warn("Host ID:{}", oe.getHostId());
        } catch (ClientException ce) {
            log.warn("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.warn("Error Message:{}", ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        //把上传之后路径返回
        return "https://" + bucketName + "." + endpoint + "/" + objectName;
    }
}
