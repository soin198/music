package org.soin.website;

import org.mybatis.spring.annotation.MapperScan;
import org.soin.core.util.RunTimeTool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 前台站点启动类
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 17:43
 **/
@SpringBootApplication
@MapperScan("org.soin.core.biz.mapper")
public class UserClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserClientApplication.class, args);
        RunTimeTool.printInfo("WebSiteApplication start-up success....");
    }

}
