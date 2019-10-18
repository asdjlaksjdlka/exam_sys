package com.qf.examsys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamsysApplicationTests {


    String str = "R817-B";


    @Test
    public void contextLoads() {

        String s1 = str.split("-")[0];
        String s2 = str.split("-")[1];

        System.out.println(s1+":"+s2);
    }

}
