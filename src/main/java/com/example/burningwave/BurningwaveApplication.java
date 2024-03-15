package com.example.burningwave;

import com.example.burningwave.bw.RuntimeClassExtender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BurningwaveApplication implements CommandLineRunner {
    @Autowired
    RuntimeClassExtender runtimeClassExtender;
    public static void main(String[] args) {
        SpringApplication.run(BurningwaveApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            runtimeClassExtender.execute();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
