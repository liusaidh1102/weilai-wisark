package com.weilai.minio;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan(value = "com.weilai.minio.mapper")
@EnableFeignClients(basePackages = "com.wisark.api.feign")
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class);
    }

}