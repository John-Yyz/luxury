package com.luxury;

import com.luxury.utils.CodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/26 18:34
 */
@SpringBootTest
@Slf4j
public class CodeUtilTest {
    @Test
    public void createCode(){
        String str= CodeUtil.createCode();
        log.info("随机编码：" + str);
        String a = UUID.randomUUID().toString().replace("-","");
        log.info("UUID :" + a);

    }
}
