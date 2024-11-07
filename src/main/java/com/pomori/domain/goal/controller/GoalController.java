package com.pomori.domain.goal.controller;

import com.pomori.domain.goal.dto.request.GoalInfoRequest;
import com.pomori.domain.goal.dto.response.GoalInfoResponse;
import com.pomori.domain.goal.dto.response.GoalListResponse;
import com.pomori.domain.goal.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RequiredArgsConstructor
@Validated
@RequestMapping("/goals")
@RestController
public class GoalController {

    private final CreateGoalService createGoalService;

    private final DeleteGoalService deleteGoalService;

    private final FindGoalService findGoalService;

    private final FindAllGoalService findAllGoalService;

    private final UpdateGoalService updateGoalService;

    @GetMapping
    public GoalListResponse getAll() {
        return findAllGoalService.getAll();
    }

    @GetMapping("/{id}")
    public GoalInfoResponse get(
            @Valid
            @NotNull
            @Positive
            @PathVariable final Long id
    ) {
        return findGoalService.find(id);
    }

    @PostMapping
    public void create(
            @Valid
            @NotNull
            @RequestBody final GoalInfoRequest request
    ) {
        createGoalService.create(request);
    }

    @PutMapping("/{id}")
    public void update(
            @Valid
            @NotNull
            @Positive
            @PathVariable final Long id,
            @Valid
            @NotNull
            @RequestBody final GoalInfoRequest request
    ) {
        updateGoalService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @Valid
            @NotNull
            @Positive
            @PathVariable final Long id
    ) {
        deleteGoalService.delete(id);
    }
}
