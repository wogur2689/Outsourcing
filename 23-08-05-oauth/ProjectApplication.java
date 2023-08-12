package com.example.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;


//@EntityScan(basePackages = {"com.example.project.domain"})
@SpringBootApplication
public class ProjectApplication {
/** DB 정해지면 application.properties 수정 후 위 annotation의 exclude 옵션 삭제 **/

//TODO 자바스크립트에 창 닫을 시 webDriver 종료시키는 코드 반드시 넣을 것 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 넣은 뒤 기존 quit 코드 수정

	@Bean
	public BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
