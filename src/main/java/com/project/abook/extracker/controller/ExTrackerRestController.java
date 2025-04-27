package com.project.abook.extracker.controller;

import com.project.abook.extracker.domain.ExTracker;
import com.project.abook.extracker.service.ExTrackerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exTracker")
public class ExTrackerRestController implements ExTrackerRestControllerDocs {

    private final ExTrackerService exTrackerService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getExTracker(@PathVariable("id") Long id) {
        ExTracker exTracker = exTrackerService.findById(id)
                .orElseThrow(() -> new RuntimeException("ExTracker not found"));

        return ResponseEntity.ok(exTracker.toString());
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveExTracker(@RequestBody ExTracker exTracker) {

        ExTracker save = exTrackerService.save(exTracker);

        return ResponseEntity.noContent().build();
    }
}
