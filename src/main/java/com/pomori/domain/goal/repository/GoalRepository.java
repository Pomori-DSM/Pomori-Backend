package com.pomori.domain.goal.repository;

import com.pomori.domain.goal.GoalEntity;
import com.pomori.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Long> {

    List<GoalEntity> findAllByUser(UserEntity user);
}
