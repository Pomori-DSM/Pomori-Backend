package com.pomori.domain.goal.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public record GoalInfoRequest(

        @Size(min = 1, max = 100)
        @NotNull
        String name,

        @NotNull
        LocalDate deadLine
) {
}
