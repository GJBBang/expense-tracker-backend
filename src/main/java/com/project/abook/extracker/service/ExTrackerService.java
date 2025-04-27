package com.project.abook.extracker.service;

import com.project.abook.extracker.domain.ExTracker;

import java.util.List;
import java.util.Optional;

public interface ExTrackerService {

    Optional<ExTracker> findById(Long id);

    List<ExTracker> findAll();

    ExTracker save(ExTracker exTracker);

    void update(ExTracker exTracker);

}
