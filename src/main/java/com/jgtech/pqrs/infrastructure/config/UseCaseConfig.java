package com.jgtech.pqrs.infrastructure.config;

import com.jgtech.pqrs.application.usecase.CreateRequestUseCase;
import com.jgtech.pqrs.domain.repository.RequestRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateRequestUseCase createRequestUseCase(
            RequestRepository repository
    ) {

        return new CreateRequestUseCase(repository);
    }
}
