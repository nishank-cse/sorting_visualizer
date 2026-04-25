package com.sortingvisualizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 👇 add this import


@SpringBootApplication
public class SortingVisualizerApplication {

    public static void main(String[] args) {

        // 👇 load .env file
        
        

        SpringApplication.run(SortingVisualizerApplication.class, args);
    }
}