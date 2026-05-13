package com.jgtech.pqrs.application.usecase;

import com.jgtech.pqrs.application.dto.CreateRequestDto;
import com.jgtech.pqrs.domain.enums.RequestStatus;
import com.jgtech.pqrs.domain.model.Request;
import com.jgtech.pqrs.domain.repository.RequestRepository;

import java.time.LocalDateTime;

public class CreateRequestUseCase {
    private final RequestRepository repository;

    public CreateRequestUseCase(RequestRepository repository){
        this.repository=repository;
    }

    public Request execute(CreateRequestDto dto){
        Request request = new Request(null,dto.title(), dto.description(), RequestStatus.PENDING, LocalDateTime.now());
        return  repository.save(request);
    }
}
