package com.pomori.domain.goal.service;

import com.pomori.domain.exception.PomoriException;
import com.pomori.domain.goal.dto.request.GoalInfoRequest;
import com.pomori.domain.goal.repository.GoalRepository;
import com.pomori.infra.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateGoalService {

    private final GoalRepository goalRepository;

    private final CurrentUser currentUser;

    public void update(final Long id, final GoalInfoRequest request) {
        var goal = goalRepository.findById(id)
                .orElseThrow(() -> new PomoriException(HttpStatus.NOT_FOUND, "Goal not found"));

        if (!goal.getUser().getId().equals(currentUser.get().getId())) {
            throw new PomoriException(HttpStatus.FORBIDDEN, "Access denied");
        }

        goal.setName(request.name());
        goal.setDeadline(request.deadLine());

        goalRepository.save(goal);
    }
}
