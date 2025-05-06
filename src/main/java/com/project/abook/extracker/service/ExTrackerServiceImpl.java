package com.project.abook.extracker.service;

import com.project.abook.extracker.domain.ExTracker;
import com.project.abook.extracker.repository.ExTrackerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExTrackerServiceImpl implements ExTrackerService {

    private final ExTrackerRepository exTrackerRepository;

    @Override
    public Optional<ExTracker> findById(Long id) {
        return exTrackerRepository.findById(id);
    }

    @Override
    public List<ExTracker> findAll() {
        return exTrackerRepository.findAll();
    }

    @Override
    public ExTracker save(ExTracker exTracker) {
        return exTrackerRepository.save(exTracker);
    }

    @Override
    public void update(ExTracker exTracker) {
        exTrackerRepository.update(exTracker);
    }
}
