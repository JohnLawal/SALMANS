package edu.mum.cs.salmans;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalmansApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SalmansApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //initialize DB records
    }
}
