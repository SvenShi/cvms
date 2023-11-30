package com.sven.cvms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CvmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CvmsApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  简历管理系统启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
