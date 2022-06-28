package com.am.adastra.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import org.springframework.stereotype.Component;


@Component
public class AlipayConfig {

    // 1. 设置参数（全局只需设置一次）
    static {
        Factory.setOptions(getOptions());
    }

    private static Config getOptions() {
        Config config = new Config();

        config.protocol = "https";

        // 沙箱环境修改为 openapi.alipaydev.com
        config.gatewayHost = "openapi.alipaydev.com";

        config.signType = "RSA2";

        config.appId = "2021000120697558";

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCZjm0HobXmEn9UpjyaxyeRZW6YdxWkB5oHarhDbAy/BQXhZkRgFfPS2hrNiMovk4Bd1eqC+sZhvbqbU8pwfu9MjAnN/cH72ZDRcJKMXJ3cka4t1Bf0l6BSof0cUuqosx6SHOWLm/k1XmRHP7Ra1u5G9vhXLbeJbhtDh8c8TECKBRAYDHc9FiO/j4FKUo/5SbrS/vv72DcU+xuOCVLKISs9tZ1b0mpz4Wqfj5KKeIjD5AN3bDjPkA1zeX22v2d70EsgaSTaSNrDQmYezF4v3StcKDcDHVS8z1TCh5ckRJVaP3qgJRpB5hXuda5MKM1rpzHotlvHxvxlHobqnPsQ25BPAgMBAAECggEAGkjI3O+PRUyRQxoZcZz5Zufn4G0x5Rvv/6kC1XefTus7MHPi7FeVntf6kogBo9cpt+Yv+C+Y2Y4ybPgQxOTVbVLjMsFm/QcmxNLyItiPpPvCxtFxMErsBHj57Gk9BZdzeis8F4OhSsNXguKiqpHzw23u4G1Q51NTk2Ggdu/OdFFZqHtbUKECrcFvtofkq86DZnKIagebQNxaqAHAS7ee7hMXTUOzcp/1bcbxBhCstB0+S6p9701nRin4ZRC7BvriFRR9iuiikRQX2Z9rE063v1fTaUOCbsdknAdtgzU5dYm/3wi0h6SphT0RRHpfCSSIysYcfTwpmkGI/KM/V4jVgQKBgQDIb9/QAFfvWZblFNwECy4zsbX6wLVjahhe+uCrDfj5NIlVs7L5lpfLqOMxI9Zp/ttOUGa1eki8Vb9+EhmRKqrC5DzNESWnnrZtJWE71kvN1uKOVFlBcueWIph4KvXGUtDOI8P7Afufi8XVIV+nGZGFsyKkLqmxQEg7e1Bb/OG8oQKBgQDEH6Ok0ZIV8DcDcwqrbeAHuWhuhzgQ89/fTKJfZ/89orVmRhAuOKDuSyVylaZ781CsjRoyjakFXK7CGHV0puhwuxZrykZDlH0p6Gk/OVmdGNbHpeP8O8KyucQXkMX2Q0kf8IW8bdldvd00L364baleRTYULD71GecnSH/JDMK27wKBgQCD5ttqjk9P+60JZAjFwHXPHwua0GaN3avcFUz0RGWr2Z7m/zKwfSG3rJSWKfdYGZdImjB9DNQA3Iz/8flRRrrJNlmsubQF8rQqb4blUcjg1KCS+X8jSvKMIogjzGGg+a7feTnnXQ6xl9HAyA2icXkKezA4y1xwgNUID8Z6QphrAQKBgDnaK2d++lzGUzQNR2RyMNeWdZvE/2Dgz3ULIgqKntwLlv7oQ/WedrpUKGO4iTee+nkr1kJ7TxRp1pMiq+MRKRe32FFLhZaBwroN+J3RyQFyRkj2Ql4xMqmN6q4Y4oxWJMfrJ0gCD1B7pTizmenFT2SjfeRNePTMA0evNyKTRQy5AoGBAJ7aHKRTMtC0d1fMXZYTAeR6WTqlNSn2vV1eMUpGObtyb99uuWG1XcP+3jndc880k77AQ5MYkdzS28zlMJLh5yDzEEMV4tHA36bGlh2MhjXLebULMaHid/3huCTWEYV7KeTvaHx4z0qJaXFOvWLNQKSRmML+T3giXu7MeaOPpHDi";

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmNHH3/fDgnlpIm+phzaiN/IWnbqz8fQfms8qOrg1mOhPRC2gFDm/hqs47nfzki/vmvxCfigqp0XlOEyIKP5gxwqMfNSCMqdeLNBMO9RZJcP/r4oN1t7VK20GVQ9+yPu96wcVgH+o1HDEDvlFRVHLtg6hlxUFBf3lv+aqN32zW+ezDTJhZ4aXtVMB+e+AjprBJz99n3/9rTaSfDnjO9enB7bP7BYHhjxQEBzViHFAW6RBulWBMS5VLlE+FjtRuelj0GCfCleSSY7TdXhk2IPCqtPAeLgvtQRl3mWbi4Xzd4savEChBBVS8Qdln+bJk0x5pY9figwgpnap7R/MMyo6TwIDAQAB";

        //可设置异步通知接收服务地址（可选）
        config.notifyUrl = "http://r9yczt.natappfree.cc/pay/callback";

        return config;
    }
}
