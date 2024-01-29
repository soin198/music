package org.soin.core.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 启动服务连接数据库
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 14:54
 **/
@Configuration
@SuppressWarnings("all")
public class DatabaseConfig implements Serializable {

    private static final long serialVersionUID = -5291938179126389090L;

    @Resource
    private HikariDataSource dataSource;

    @PostConstruct
    public void init() throws ClassNotFoundException, URISyntaxException, SQLException {
        //通过hikari获取数据库连接信息
        String driver = dataSource.getDriverClassName();
        String url = dataSource.getJdbcUrl();
        String username = dataSource.getUsername();
        String password = dataSource.getPassword();
        Class.forName(driver);
        URI uri = new URI(url.replace("jdbc:", ""));
        String host = uri.getHost();
        int port = uri.getPort();
        String path = uri.getPath();
        String connectUrl = "jdbc:mysql://" + host + ":" + port;
        try (Connection connection = DriverManager.getConnection(connectUrl, username, password);
             Statement statement = connection.createStatement()) {
            // 创建数据库
            statement.executeUpdate("`CREATE DATABASE IF NOT EXISTS `" + path.replace("/", "") + "` DEFAULT CHARACTER SET = `utf8` COLLATE `utf8_general_ci`;");
        }
    }


}
