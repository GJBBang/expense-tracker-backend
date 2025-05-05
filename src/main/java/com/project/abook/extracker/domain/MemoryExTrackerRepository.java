package com.project.abook.extracker.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryExTrackerRepository implements ExTrackerRepository {

    private static final Map<Long, ExTracker> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Optional<ExTracker> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<ExTracker> findAll() {
        return List.of();
    }

    @Override
    public ExTracker save(ExTracker exTracker) {
        store.put(sequence++, exTracker);

        return exTracker;
    }

    @Override
    public void update(ExTracker exTracker) {

    }

    public void clearStore() {
        store.clear();
    }
}
