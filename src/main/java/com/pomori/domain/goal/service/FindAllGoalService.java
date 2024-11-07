package com.pomori.domain.goal.service;

import com.pomori.domain.annotation.ReadOnlyUseCase;
import com.pomori.domain.exception.PomoriException;
import com.pomori.domain.goal.dto.response.GoalInfoResponse;
import com.pomori.domain.goal.dto.response.GoalListResponse;
import com.pomori.domain.goal.repository.GoalRepository;
import com.pomori.infra.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class FindAllGoalService {

    private final GoalRepository goalRepository;

    private final CurrentUser currentUser;

    public GoalListResponse getAll() {
        var goals = goalRepository.findAllByUser(currentUser.get());

        if (goals.isEmpty()) {
            throw new PomoriException(HttpStatus.NO_CONTENT, "User did not set any goals");
        }

        return GoalListResponse.with(
                goals.stream().map(GoalInfoResponse::of).toList()
        );
    }
}
