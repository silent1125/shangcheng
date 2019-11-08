package com.taotao;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*@SpringBootApplication*/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//加载mapper 接口
/*@MapperScan(basePackages = "com.taotao.mapper")*/
public class WebApplication1 {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication1.class, args);
    }

}
