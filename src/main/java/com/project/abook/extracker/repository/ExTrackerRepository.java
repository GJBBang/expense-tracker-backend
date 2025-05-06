package com.project.abook.extracker.repository;

import com.project.abook.extracker.domain.ExTracker;

import java.util.List;
import java.util.Optional;

public interface ExTrackerRepository {

    Optional<ExTracker> findById(Long id);

    List<ExTracker> findAll();

    ExTracker save(ExTracker exTracker);

    void update(ExTracker exTracker);

}
