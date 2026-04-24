package com.sortingvisualizer.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * CORS Configuration — allows the React frontend to call the Spring Boot backend.
 *
 * In the original Node.js backend this was done with:
 *   const cors = require('cors');
 *   app.use(cors());
 *
 * Here we replicate that behaviour for the React frontend URLs.
 * Update allowedOrigins to match your actual Vercel deployment URL.
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Allow React dev server and your Vercel frontend
        config.setAllowedOrigins(List.of(
                "http://localhost:3000",                                              // local dev
                "https://sorting-visualizer-g1mmdi313-nishank-mukhijas-projects.vercel.app",  // vercel
                "https://sorting-visualizer-plum-seven.vercel.app"                   // vercel alt
        ));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }
}
