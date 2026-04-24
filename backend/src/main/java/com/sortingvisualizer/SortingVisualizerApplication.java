package com.sortingvisualizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 👇 add this import
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class SortingVisualizerApplication {

    public static void main(String[] args) {

        // 👇 load .env file
        Dotenv dotenv = Dotenv.load();
        System.setProperty("MONGO_URI", dotenv.get("MONGO_URI"));

        SpringApplication.run(SortingVisualizerApplication.class, args);
    }
}