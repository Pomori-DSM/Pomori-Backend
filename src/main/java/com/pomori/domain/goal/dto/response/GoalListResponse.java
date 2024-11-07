package com.pomori.domain.goal.dto.response;

import java.util.List;

public record GoalListResponse(
        List<GoalInfoResponse> list
) {

    public static GoalListResponse with(List<GoalInfoResponse> list) {
        return new GoalListResponse(List.copyOf(list));
    }
}
