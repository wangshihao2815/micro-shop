package com.leyou.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author wangshihao
 * @Date 2020/3/19
 * @Since 1.0.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.leyou.user.mapper")
public class LeyouUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouUserServiceApplication.class, args);
    }
}
