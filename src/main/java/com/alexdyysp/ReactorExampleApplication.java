package com.alexdyysp;

import java.io.IOException;

import com.alexdyysp.setup.CassandraSetup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactorExampleApplication {

    public static void main(String[] args) throws IOException {
        CassandraSetup.init();
        SpringApplication.run(ReactorExampleApplication.class, args);
    }

}