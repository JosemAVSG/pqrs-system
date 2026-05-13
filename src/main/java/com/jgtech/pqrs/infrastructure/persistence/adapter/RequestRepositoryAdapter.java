package com.jgtech.pqrs.infrastructure.persistence.adapter;

import com.jgtech.pqrs.domain.model.Request;
import com.jgtech.pqrs.domain.repository.RequestRepository;
import com.jgtech.pqrs.infrastructure.persistence.mapper.RequestMapper;
import com.jgtech.pqrs.infrastructure.persistence.repository.JpaRequestRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RequestRepositoryAdapter implements RequestRepository {

    private final JpaRequestRepository repository;

    public RequestRepositoryAdapter(
            JpaRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Request save(Request request) {
        return RequestMapper.toDomain(repository.save(
                RequestMapper.toEntity(request)));
    }

    @Override
    public List<Request> findAll() {
        return repository.findAll()
                .stream()
                .map(RequestMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Request> findById(Long id) {
        return repository.findById(id)
                .map(RequestMapper::toDomain);
    }
}
