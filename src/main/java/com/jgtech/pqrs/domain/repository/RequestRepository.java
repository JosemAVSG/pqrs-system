package com.jgtech.pqrs.domain.repository;

import com.jgtech.pqrs.domain.model.Request;

import java.util.List;
import java.util.Optional;

public interface RequestRepository {
    Request save (Request request);
    List<Request> findAll();
    Optional<Request> findById(Long id);
}
