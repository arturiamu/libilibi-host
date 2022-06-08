package com.am.adastra;

import com.am.adastra.util.VideoPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdAstraApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdAstraApplication.class, args);
        VideoPool.run();
    }
}
