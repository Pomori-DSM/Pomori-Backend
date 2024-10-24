package com.pomori.domain.goal.repository;

import com.pomori.domain.goal.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Long> {
}
