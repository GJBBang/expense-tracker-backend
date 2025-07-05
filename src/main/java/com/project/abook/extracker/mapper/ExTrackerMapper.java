package com.project.abook.extracker.mapper;

import com.project.abook.extracker.domain.ExTracker;
import com.project.abook.extracker.dto.response.ExTrackerDetailResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExTrackerMapper {

    ExTrackerDetailResponse toExTrackerDetailResponse(ExTracker exTracker);
}
