package com.project.abook.extracker.controller;

import com.project.abook.extracker.domain.ExTracker;
import com.project.abook.extracker.service.ExTrackerService;
import com.project.abook.global.exception.BusinessException;
import com.project.abook.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exTrackers")
public class ExTrackerRestController implements ExTrackerRestControllerDocs {

    private final ExTrackerService exTrackerService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getExTracker(@PathVariable("id") Long id) {
        ExTracker exTracker = exTrackerService.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXTRACKER_NOT_FOUND_BY_EXTRACKER_ID));

        return ResponseEntity.ok(exTracker.toString());
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveExTracker(@RequestBody ExTracker exTracker) {

        ExTracker save = exTrackerService.save(exTracker);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ExTracker>> getAllExTrackers() {
        List<ExTracker> exTrackers = exTrackerService.findAll();

        return ResponseEntity.ok(exTrackers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateExTracker(@RequestBody ExTracker exTracker) {

        return ResponseEntity.noContent().build();
    }
}
