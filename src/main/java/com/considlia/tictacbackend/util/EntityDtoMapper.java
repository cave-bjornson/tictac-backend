package com.considlia.tictacbackend.util;

import com.considlia.tictacbackend.domain.model.Activity;
import com.considlia.tictacbackend.domain.model.Project;
import com.considlia.tictacbackend.domain.model.TimeLog;
import com.considlia.tictacbackend.dto.ActivityDto;
import com.considlia.tictacbackend.dto.ProjectDto;
import com.considlia.tictacbackend.dto.TimeLogDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EntityDtoMapper {

    private final ModelMapper modelMapper;

    public EntityDtoMapper() {
        Converter<Duration, String> durationStringConverter = context -> context.getSource().toString();
        Converter<String, Duration> stringDurationConverter = context -> Duration.parse(context.getSource());
        Converter<LocalDateTime, OffsetDateTime> dateTimeConverter = context -> context.getSource().atOffset(ZoneOffset.UTC);
        Converter<OffsetDateTime, LocalDateTime> offsetConverter = context -> context.getSource().toLocalDateTime();

        Converter<Set<ActivityDto>, Set<Activity>> dtoProjectSetConverter = new Converter<>() {
            @Override
            public Set<Activity> convert(MappingContext<Set<ActivityDto>, Set<Activity>> context) {
                return context.getSource()
                              .stream()
                              .map(activityDto -> modelMapper.map(activityDto, Activity.class))
                              .collect(Collectors.toSet());
            }
        };

        Converter<Set<TimeLogDto>, Set<TimeLog>> dtoTimeLogSetConverter = new Converter<>() {
            @Override
            public Set<TimeLog> convert(MappingContext<Set<TimeLogDto>, Set<TimeLog>> context) {
                return context.getSource()
                              .stream()
                              .map(TimeLogDto -> modelMapper.map(TimeLogDto, TimeLog.class))
                              .collect(Collectors.toSet());
            }
        };

        modelMapper = new ModelMapper();
        modelMapper.typeMap(TimeLog.class, TimeLogDto.class)
                   .addMappings(mapping -> mapping.using(durationStringConverter).map(TimeLog::getDuration, TimeLogDto::setDuration))
                   .addMappings(mapping -> mapping.using(dateTimeConverter).map(TimeLog::getLocalDateTime, TimeLogDto::setDateTime));
        modelMapper.typeMap(TimeLogDto.class, TimeLog.class)
                   .addMappings(mapping -> mapping.using(offsetConverter).map(TimeLogDto::getDateTime, TimeLog::setLocalDateTime))
                   .addMappings(mapping -> mapping.using(stringDurationConverter).map(TimeLogDto::getDuration, TimeLog::setDuration));

        modelMapper.typeMap(ProjectDto.class, Project.class)
                   .addMappings(mapping -> mapping.using(dtoProjectSetConverter).map(ProjectDto::getActivities, Project::setActivities));
//        modelMapper.typeMap(ActivityDto.class, Activity.class)
//                   .addMappings(mapping -> mapping.using(dtoTimeLogSetConverter).map(ActivityDto::getTimeLogs, Activity::setTimeLogs));


    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}