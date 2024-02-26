package org.soin.executor;

import org.soin.executor.core.util.RunTimeTool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 执行器启动类
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-26 03:00
 **/
@SpringBootApplication(scanBasePackages = {"org.soin.executor"})
public class ExecutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExecutorApplication.class, args);
        RunTimeTool.printInfo("ExecutorApplication start-up success....");
    }
}
