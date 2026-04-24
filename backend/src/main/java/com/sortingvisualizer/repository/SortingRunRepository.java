package com.sortingvisualizer.repository;

import com.sortingvisualizer.model.SortingRun;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MongoDB repository for SortingRun documents.
 * Extends MongoRepository to get CRUD operations for free —
 * equivalent to Mongoose model methods in the original Node.js backend.
 */
@Repository
public interface SortingRunRepository extends MongoRepository<SortingRun, String> {

    // Find all runs for a specific algorithm (e.g. "Bubble Sort")
    List<SortingRun> findByAlgorithmOrderByCreatedAtDesc(String algorithm);

    // Find the most recent N runs
    List<SortingRun> findTop10ByOrderByCreatedAtDesc();
}
