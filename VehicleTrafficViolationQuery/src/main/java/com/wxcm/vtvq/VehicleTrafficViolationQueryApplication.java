package com.wxcm.vtvq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VehicleTrafficViolationQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleTrafficViolationQueryApplication.class, args);
    }

}
