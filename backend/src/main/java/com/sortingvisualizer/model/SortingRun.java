package com.sortingvisualizer.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Represents a single sorting run saved to MongoDB.
 * Maps to the "runs" collection — equivalent to the Mongoose schema
 * in the original Node.js backend.
 *
 * Fields:
 *  - algorithm  : name of sorting algorithm (e.g. "Bubble Sort")
 *  - timeTaken  : milliseconds the sort took to complete
 *  - arraySize  : number of elements in the array that was sorted
 *  - createdAt  : timestamp auto-set on document creation
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "runs")
public class SortingRun {

    @Id
    private String id;

    @NotBlank(message = "Algorithm name is required")
    private String algorithm;

    @NotNull(message = "Time taken is required")
    @Min(value = 0, message = "Time taken must be non-negative")
    private Double timeTaken;   // in milliseconds

    @NotNull(message = "Array size is required")
    @Min(value = 1, message = "Array size must be at least 1")
    private Integer arraySize;

    @CreatedDate
    private LocalDateTime createdAt;
}
