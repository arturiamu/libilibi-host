package com.am.imadeCode;

import com.am.adastra.util.EmailUtil;
import com.am.adastra.util.ImgValidateCodeUtil;
import org.junit.jupiter.api.Test;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/6 14:49
 * @Description:
 */
public class imageTests {
    @Test
    public void send(){
        ImgValidateCodeUtil.getImgCodeBaseCode(4);
    }
}
