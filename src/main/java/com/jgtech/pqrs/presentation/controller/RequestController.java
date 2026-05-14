package com.jgtech.pqrs.presentation.controller;

import com.jgtech.pqrs.application.dto.CreateRequestDto;
import com.jgtech.pqrs.application.usecase.CreateRequestUseCase;
import com.jgtech.pqrs.domain.model.Request;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requests")
public class RequestController {
    private final CreateRequestUseCase useCase;

    public RequestController(
            CreateRequestUseCase useCase
    ) {
        this.useCase = useCase;
    }

    @PostMapping
    public Request create(
            @RequestBody CreateRequestDto dto
    ) {

        return useCase.execute(dto);
    }
}
