package org.soin.executor;

import org.mybatis.spring.annotation.MapperScan;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 执行器启动类
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 17:43
 **/
@SpringBootApplication(scanBasePackages = {"org.soin.executor.*", "org.soin.core.*"})
@MapperScan("org.soin.core.infrastructure.mapper")
public class ExecutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExecutorApplication.class, args);
        RunTimeTool.printInfo("executor module build success..");
    }

}
