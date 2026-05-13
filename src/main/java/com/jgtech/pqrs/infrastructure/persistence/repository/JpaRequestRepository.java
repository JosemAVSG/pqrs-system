package com.jgtech.pqrs.infrastructure.persistence.repository;

import com.jgtech.pqrs.infrastructure.persistence.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRequestRepository extends JpaRepository<RequestEntity, Long> {
}
