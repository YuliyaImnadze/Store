package com.example.store.mapper;

import com.example.store.dto.notification.NotificationDtoRequest;
import com.example.store.dto.notification.NotificationDtoResponse;
import com.example.store.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificationMapper extends CommonMapper<Notification, NotificationDtoRequest, NotificationDtoResponse> {

    @Override
    @Mapping(target = "recipient.id", source = "recipientId")
    Notification toEntityFromRequest(NotificationDtoRequest dto);
}
