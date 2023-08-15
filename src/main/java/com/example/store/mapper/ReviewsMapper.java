package com.example.store.mapper;

import com.example.store.dto.reviews.ReviewsDtoRequest;
import com.example.store.dto.reviews.ReviewsDtoResponse;
import com.example.store.entity.Reviews;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewsMapper extends CommonMapper<Reviews, ReviewsDtoRequest, ReviewsDtoResponse> {
}
