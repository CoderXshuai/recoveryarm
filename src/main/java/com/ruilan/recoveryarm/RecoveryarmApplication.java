package com.ruilan.recoveryarm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication(scanBasePackages = {"com.ruilan.recoveryarm"})
@MapperScan(
        basePackages = "com.ruilan.recoveryarm.dao",
        annotationClass = Repository.class
)
public class RecoveryarmApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecoveryarmApplication.class, args);
    }

}
