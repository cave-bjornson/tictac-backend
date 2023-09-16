package com.considlia.tictacbackend.dto;

import com.considlia.tictacbackend.domain.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class ProjectsByUserDto {
    Long id;
    String username;
    @JsonProperty("projects")
    List<ProjectDto> projectDtos;

    public ProjectsByUserDto(User user, List<ProjectDto> projectDtos) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.projectDtos = projectDtos;
    }
}
