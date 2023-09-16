package com.considlia.tictacbackend.api;

import com.considlia.tictacbackend.domain.model.Project;
import com.considlia.tictacbackend.dto.ProjectDto;
import com.considlia.tictacbackend.dto.ProjectsByUserDto;
import com.considlia.tictacbackend.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = BaseController.PATH + "/project")
@Tag(
        name = "Projects API",
        description = "A simple API for managing projects"
)
public class ProjectController extends BaseController<Project, Long, ProjectDto> {

    final ProjectService projectService;

    ProjectController(ProjectService projectService) {
        super(projectService);
        this.projectService = projectService;
    }

    @Operation(summary = "Get all available projects")
    @GetMapping
    ResponseEntity<List<ProjectDto>> getAllProjects() {
        return super.getAll();
    }

    @Operation(summary = "Get a single project")
    @GetMapping("/{id}")
    ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        return super.get(id);
    }

    @Operation(summary = "Get projects with a specified user")
    @GetMapping("/user/{id}")
    ResponseEntity<ProjectsByUserDto> getProjectsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getAllProjectsByUserId(id));
    }

    @Operation(summary = "Update an existing project")
    @PutMapping("/{id}")
    ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(projectService.updateProject(id, projectDto));
    }


}
