package com.jgtech.pqrs.infrastructure.persistence.mapper;

import com.jgtech.pqrs.domain.model.Request;
import com.jgtech.pqrs.infrastructure.persistence.entity.RequestEntity;

public class RequestMapper {

    public static RequestEntity toEntity(Request request) {
        return new RequestEntity(
                request.getId(),
                request.getTitle(),
                request.getDescription(),
                request.getStatus(),
                request.getCreatedAt());
    }

    public static Request toDomain(RequestEntity entity) {
        return new Request(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedAt());
    }
}
