package com.project.abook.extracker.service;

import com.project.abook.extracker.domain.ExTracker;
import com.project.abook.extracker.repository.ExTrackerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExTrackerService {

    private final ExTrackerRepository exTrackerRepository;

    public Optional<ExTracker> findById(Long id) {
        return exTrackerRepository.findById(id);
    }

    public List<ExTracker> findAll() {
        return exTrackerRepository.findAll();
    }

    public ExTracker save(ExTracker exTracker) {
        return exTrackerRepository.save(exTracker);
    }
}
