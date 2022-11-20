package com.luxury;

import com.luxury.utils.QiNiuUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LuxuryApplicationTests {

    @Test
    public void test1(){
        QiNiuUtil.upload("F:\\p.png","aa",false);
    }

}
