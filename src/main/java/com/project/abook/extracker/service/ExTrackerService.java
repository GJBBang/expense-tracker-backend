package com.project.abook.extracker.service;

import com.project.abook.extracker.domain.ExTracker;
import com.project.abook.extracker.dto.response.ExTrackerDetailResponse;
import com.project.abook.extracker.mapper.ExTrackerMapper;
import com.project.abook.extracker.repository.ExTrackerRepository;
import com.project.abook.global.exception.BusinessException;
import com.project.abook.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExTrackerService {

    private final ExTrackerRepository exTrackerRepository;

    private final ExTrackerMapper exTrackerMapper;

    public ExTrackerDetailResponse findById(Long id) {

        ExTracker exTracker = exTrackerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXTRACKER_NOT_FOUND_BY_EXTRACKER_ID));

        ExTrackerDetailResponse exTrackerDetailResponse = exTrackerMapper.toExTrackerDetailResponse(exTracker);
        return exTrackerDetailResponse;
    }

    public List<ExTracker> findAll() {
        return exTrackerRepository.findAll();
    }

    public ExTracker save(ExTracker exTracker) {
        return exTrackerRepository.save(exTracker);
    }
}
