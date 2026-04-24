package com.sortingvisualizer.service;

import com.sortingvisualizer.model.SortingRun;
import com.sortingvisualizer.repository.SortingRunRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service layer — contains all business logic for sorting runs.
 * Sits between the Controller and Repository, matching the
 * separation of concerns from the original Express route handlers.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SortingRunService {

    private final SortingRunRepository repository;

    /**
     * GET /api/runs — fetch all sorting run history
     * Equivalent to: SortingRun.find({}).sort({ createdAt: -1 }) in Mongoose
     */
    public List<SortingRun> getAllRuns() {
        log.debug("Fetching all sorting runs");
        return repository.findAll();
    }

    /**
     * POST /api/runs — save a new sorting run
     * Equivalent to: new SortingRun({...}).save() in Mongoose
     */
    public SortingRun saveRun(SortingRun run) {
        run.setCreatedAt(LocalDateTime.now());
        SortingRun saved = repository.save(run);
        log.debug("Saved sorting run: id={}, algorithm={}", saved.getId(), saved.getAlgorithm());
        return saved;
    }

    /**
     * GET /api/runs/{id} — fetch a single run by its MongoDB ID
     */
    public Optional<SortingRun> getRunById(String id) {
        return repository.findById(id);
    }

    /**
     * DELETE /api/runs/{id} — delete a run by ID
     */
    public boolean deleteRun(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            log.debug("Deleted sorting run: id={}", id);
            return true;
        }
        return false;
    }

    /**
     * GET /api/runs/algorithm/{name} — filter runs by algorithm name
     */
    public List<SortingRun> getRunsByAlgorithm(String algorithm) {
        return repository.findByAlgorithmOrderByCreatedAtDesc(algorithm);
    }

    /**
     * GET /api/runs/recent — last 10 runs
     */
    public List<SortingRun> getRecentRuns() {
        return repository.findTop10ByOrderByCreatedAtDesc();
    }

    /**
     * DELETE /api/runs — clear all run history
     */
    public long clearAllRuns() {
        long count = repository.count();
        repository.deleteAll();
        log.info("Cleared all {} sorting runs", count);
        return count;
    }
}
