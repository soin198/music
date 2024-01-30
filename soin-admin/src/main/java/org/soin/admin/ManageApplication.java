package org.soin.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.soin.core.util.RunTimeTool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 后台启动类
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 10:34
 **/
@SpringBootApplication(scanBasePackages = {"org.soin.core"})
@MapperScan("org.soin.core.biz.mapper")
public class ManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
        RunTimeTool.printInfo("AdminApplication start-up success....");
    }
}
