package org.soin.core.infrastructure.config;

import com.zaxxer.hikari.HikariDataSource;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 启动创建数据库
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-06-26 10:49
 **/
@Configuration
public class DatabaseConfig {

    @Resource
    private HikariDataSource dataSource;

    /**
     * 创建数据库连接
     */
    @PostConstruct
    public void create() {
        String driver = dataSource.getDriverClassName();
        String url = dataSource.getJdbcUrl();
        String username = dataSource.getUsername();
        String password = dataSource.getPassword();
        try {
            Class.forName(driver);
            String uriNew = url.replace("jdbc:", "");
            URI uri = new URI(uriNew);
            String host = uri.getHost();
            int port = uri.getPort();
            String path = uri.getPath();
            String connectUrl = ("jdbc:mysql://" + host + ":" + port);
            try (Connection connection = DriverManager.getConnection(connectUrl, username, password);
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate("CREATE DATABASE IF NOT EXISTS `" + path.replace("/", "") + "` DEFAULT CHARACTER SET = `utf8` COLLATE `utf8_general_ci`;");
            }
        } catch (Exception exception) {
            RunTimeTool.printError(exception);
        }
    }

}
