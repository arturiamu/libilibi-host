package com.am.email;

import com.am.adastra.util.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/6 13:20
 * @Description:
 */
public class EmailTests {

    @Test
    public void send(){
        EmailUtil.sendEmail("老谢","我邮箱验证写完了");
    }
}
