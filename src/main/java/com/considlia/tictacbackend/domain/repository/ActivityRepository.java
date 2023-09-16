package com.considlia.tictacbackend.domain.repository;

import com.considlia.tictacbackend.domain.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
