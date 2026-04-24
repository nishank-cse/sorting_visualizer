package com.sortingvisualizer.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * Enables @CreatedDate and @LastModifiedDate annotations on MongoDB documents.
 * This auto-populates the createdAt field on SortingRun when saved.
 */
@Configuration
@EnableMongoAuditing
public class MongoConfig {
}
