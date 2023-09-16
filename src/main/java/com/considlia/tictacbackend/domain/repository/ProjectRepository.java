package com.considlia.tictacbackend.domain.repository;

import com.considlia.tictacbackend.domain.model.Project;
import com.considlia.tictacbackend.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByUsersContains(User user);
}
