package com.considlia.tictacbackend.domain.repository;


import com.considlia.tictacbackend.domain.model.TimeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeLogRepository extends JpaRepository<TimeLog, Long> {
}
