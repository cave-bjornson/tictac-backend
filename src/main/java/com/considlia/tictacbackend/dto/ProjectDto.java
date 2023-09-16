package com.considlia.tictacbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class ProjectDto implements Dto {
    private Long id;
    private String title;
    @JsonProperty("activities")
    private Set<ActivityDto> activities;
}
