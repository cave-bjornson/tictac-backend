package com.considlia.tictacbackend.service;

import com.considlia.tictacbackend.domain.model.Project;
import com.considlia.tictacbackend.domain.model.User;
import com.considlia.tictacbackend.domain.repository.ProjectRepository;
import com.considlia.tictacbackend.domain.repository.UserRepository;
import com.considlia.tictacbackend.dto.ProjectDto;
import com.considlia.tictacbackend.dto.ProjectsByUserDto;
import com.considlia.tictacbackend.service.exception.ResourceNotFoundException;
import com.considlia.tictacbackend.util.EntityDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService extends CrudService<Project, Long, ProjectDto> {

    final ProjectRepository projectRepository;
    final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, EntityDtoMapper entityDtoMapper) {
        super(projectRepository, Project.class, ProjectDto.class, entityDtoMapper);
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public ProjectsByUserDto getAllProjectsByUserId(Long id) {
        User user = userRepository.findById(id).get();
        List<ProjectDto> projectDtos = projectRepository.findAllByUsersContains(user)
                                                        .stream()
                                                        .map(project -> modelMapper.map(project, ProjectDto.class))
                                                        .collect(Collectors.toList());
        return new ProjectsByUserDto(user, projectDtos);
    }

    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        Project projectToUpdate = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No " + entityClass.getName() + "with" + id + "found"));
        modelMapper.map(projectDto, projectToUpdate);
        projectToUpdate.getActivities().forEach(activity -> activity.setProject(projectToUpdate));
        return modelMapper.map(jpaRepository.save(projectToUpdate), ProjectDto.class);
    }
}
