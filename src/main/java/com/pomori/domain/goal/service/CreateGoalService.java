package com.pomori.domain.goal.service;

import com.pomori.domain.goal.GoalEntity;
import com.pomori.domain.goal.dto.request.GoalInfoRequest;
import com.pomori.domain.goal.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateGoalService {

    private final GoalRepository goalRepository;

    public void create(final GoalInfoRequest request) {

        goalRepository.save(
                GoalEntity.builder()
                        .name(request.name())
                        .deadline(request.deadLine())
                        .build()
        );
    }
}
