package com.sortingvisualizer.controller;

import com.sortingvisualizer.model.ApiResponse;
import com.sortingvisualizer.model.SortingRun;
import com.sortingvisualizer.service.SortingRunService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller for Sorting Run APIs.
 *
 * Node.js/Express → Spring Boot mapping:
 *  app.get('/api/runs')          → GET  /api/runs
 *  app.post('/api/runs')         → POST /api/runs
 *  app.get('/api/runs/:id')      → GET  /api/runs/{id}
 *  app.delete('/api/runs/:id')   → DELETE /api/runs/{id}
 *
 * Extra endpoints added:
 *  GET  /api/runs/recent                  → last 10 runs
 *  GET  /api/runs/algorithm/{name}        → filter by algorithm
 *  DELETE /api/runs                       → clear all history
 *  GET  /api/health                       → health check
 */
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SortingRunController {

    private final SortingRunService service;

    // ─── Health Check ────────────────────────────────────────────────────────────

    /**
     * GET /api/health
     * Simple health check — confirms the backend is up.
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Map<String, String>>> health() {
        return ResponseEntity.ok(ApiResponse.success("Server is running", Map.of(
                "status", "UP",
                "service", "Sorting Visualizer Backend (Spring Boot)"
        )));
    }

    // ─── Sorting Runs ────────────────────────────────────────────────────────────

    /**
     * GET /api/runs
     * Returns all sorting run history from MongoDB.
     * Direct replacement for: router.get('/', async (req, res) => { ... })
     */
    @GetMapping("/runs")
    public ResponseEntity<ApiResponse<List<SortingRun>>> getAllRuns() {
        List<SortingRun> runs = service.getAllRuns();
        return ResponseEntity.ok(ApiResponse.success("Fetched " + runs.size() + " runs", runs));
    }

    /**
     * POST /api/runs
     * Saves a new sorting run to MongoDB.
     * Body: { "algorithm": "Bubble Sort", "timeTaken": 123.45, "arraySize": 50 }
     *
     * Direct replacement for: router.post('/', async (req, res) => { ... })
     */
    @PostMapping("/runs")
    public ResponseEntity<ApiResponse<SortingRun>> saveRun(@Valid @RequestBody SortingRun run) {
        SortingRun saved = service.saveRun(run);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Sorting run saved successfully", saved));
    }

    /**
     * GET /api/runs/recent
     * Returns the 10 most recent sorting runs.
     */
    @GetMapping("/runs/recent")
    public ResponseEntity<ApiResponse<List<SortingRun>>> getRecentRuns() {
        List<SortingRun> runs = service.getRecentRuns();
        return ResponseEntity.ok(ApiResponse.success("Recent runs fetched", runs));
    }

    /**
     * GET /api/runs/algorithm/{name}
     * Returns all runs filtered by algorithm name.
     * Example: GET /api/runs/algorithm/Bubble Sort
     */
    @GetMapping("/runs/algorithm/{name}")
    public ResponseEntity<ApiResponse<List<SortingRun>>> getRunsByAlgorithm(
            @PathVariable String name) {
        List<SortingRun> runs = service.getRunsByAlgorithm(name);
        return ResponseEntity.ok(ApiResponse.success("Runs for algorithm: " + name, runs));
    }

    /**
     * GET /api/runs/{id}
     * Returns a single sorting run by its MongoDB document ID.
     */
    @GetMapping("/runs/{id}")
    public ResponseEntity<ApiResponse<SortingRun>> getRunById(@PathVariable String id) {
        return service.getRunById(id)
                .map(run -> ResponseEntity.ok(ApiResponse.success("Run found", run)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Run not found with id: " + id)));
    }

    /**
     * DELETE /api/runs/{id}
     * Deletes a specific sorting run by ID.
     */
    @DeleteMapping("/runs/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRun(@PathVariable String id) {
        boolean deleted = service.deleteRun(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.success("Run deleted successfully", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Run not found with id: " + id));
    }

    /**
     * DELETE /api/runs
     * Clears ALL run history from the database.
     */
    @DeleteMapping("/runs")
    public ResponseEntity<ApiResponse<Map<String, Long>>> clearAllRuns() {
        long deleted = service.clearAllRuns();
        return ResponseEntity.ok(ApiResponse.success(
                "All runs cleared", Map.of("deletedCount", deleted)));
    }
}
