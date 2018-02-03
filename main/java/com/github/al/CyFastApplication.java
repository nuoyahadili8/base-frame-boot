package com.github.al;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.github.al.dao")
public class CyFastApplication {

	public static void main(String[] args) {
		SpringApplication.run(CyFastApplication.class, args);
	}
}
