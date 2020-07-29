package com.alexdyysp.setup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.sql.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource("classpath:application.properties")
public class MysqlSetup {

    @Value(value = "${spring.datasource.driver-class-name}")
    private String driver;

    @Value(value = "${spring.datasource.url}")
    private String url;

    @Value(value = "${spring.datasource.username}")
    private String userName;

    @Value(value = "${spring.datasource.password}")
    private String password;


    @Test
    public void init() throws SQLException, ClassNotFoundException{
        // 连接数据库
        System.out.println(driver+url+userName+password);
        Class.forName(driver);
        // 测试url中是否包含useSSL字段，没有则添加设该字段且禁用
        if(url.indexOf("?") == -1){
            url = url + "?useSSL=false" ;
        }
        else if(url.indexOf("useSSL=false") == -1 || url.indexOf("useSSL=true") == -1) {
            url = url + "&useSSL=false";
        }
        Connection conn = DriverManager.getConnection(url, userName, password);
        Statement stat = conn.createStatement();
        // 获取数据库表名
        ResultSet rs = conn.getMetaData().getTables(null, null, "testForReactor", null);

        // 判断表是否存在，如果存在则什么都不做，否则创建表
        if(rs.next()){
            return;
        }
        else{
            // 创建博客数据库表
            stat.execute("CREATE TABLE BLOGPOST(" +
                    "ID varchar(64) NOT NULL COMMENT '唯一指定ID，使用UUID'," +
                    "TITLE TEXT COMMENT '博客标题'," +
                    "AUTHOR varchar(32) COMMENT '作者'," +
                    "BODY TEXT COMMENT '博客内容'," +
                    "PRIMARY KEY (ID))");
        }
        // 释放资源
        stat.close();
        conn.close();
    }
}
