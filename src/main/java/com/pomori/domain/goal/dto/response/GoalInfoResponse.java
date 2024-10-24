package com.pomori.domain.goal.dto.response;

import com.pomori.domain.goal.GoalEntity;

import java.time.LocalDate;

public record GoalInfoResponse(
        Long id,
        String name,
        LocalDate deadLine
) {

    public static GoalInfoResponse of(GoalEntity entity) {
        return new GoalInfoResponse(
                entity.getId(),
                entity.getName(),
                entity.getDeadline()
        );
    }
}
