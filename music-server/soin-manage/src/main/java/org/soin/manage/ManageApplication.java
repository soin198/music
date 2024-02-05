package org.soin.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.soin.core.infrastructure.utils.RunTimeTool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 后台启动类
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 10:34
 **/
@SpringBootApplication(scanBasePackages = {"org.soin.manage", "org.soin.core.*"})
@MapperScan("org.soin.core.infrastructure.mappers")
public class ManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
        RunTimeTool.printInfo("AdminApplication start-up success....");
    }
}
