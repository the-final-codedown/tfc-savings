package fr.polytech.al.tfc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TfcSavingsApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(TfcSavingsApplication.class, args);
    }

    @Override
    public void run(String... args) {
    }


}
