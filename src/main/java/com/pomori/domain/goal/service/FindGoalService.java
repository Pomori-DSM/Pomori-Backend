package com.pomori.domain.goal.service;

import com.pomori.domain.exception.PomoriException;
import com.pomori.domain.goal.dto.response.GoalInfoResponse;
import com.pomori.domain.goal.repository.GoalRepository;
import com.pomori.infra.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FindGoalService {

    private final GoalRepository goalRepository;

    private final CurrentUser currentUser;

    public GoalInfoResponse find(final Long id) {
        var goal = goalRepository.findById(id)
                .orElseThrow(() -> new PomoriException(HttpStatus.NOT_FOUND, "Goal not found"));

        if (!goal.getUser().getId().equals(currentUser.get().getId())) {
            throw new PomoriException(HttpStatus.FORBIDDEN, "Access denied");
        }

        return GoalInfoResponse.of(goal);
    }
}
