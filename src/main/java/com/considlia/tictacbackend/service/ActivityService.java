package com.considlia.tictacbackend.service;

import com.considlia.tictacbackend.domain.model.Activity;
import com.considlia.tictacbackend.domain.model.TimeLog;
import com.considlia.tictacbackend.domain.repository.ActivityRepository;
import com.considlia.tictacbackend.domain.repository.TimeLogRepository;
import com.considlia.tictacbackend.dto.ActivityDto;
import com.considlia.tictacbackend.dto.TimeLogDto;
import com.considlia.tictacbackend.util.EntityDtoMapper;
import org.springframework.stereotype.Service;

@Service
public class ActivityService extends CrudService<Activity, Long, ActivityDto> {

    final TimeLogRepository timeLogRepository;

    public ActivityService(ActivityRepository activityRepository, EntityDtoMapper entityDtoMapper, TimeLogRepository timeLogRepository) {
        super(activityRepository, Activity.class, ActivityDto.class, entityDtoMapper);
        this.timeLogRepository = timeLogRepository;
    }

    @Override
    public ActivityDto update(Long id, ActivityDto activityDto) {
        Activity activity = getEntity(id);
        modelMapper.map(activityDto, activity);
        activity.getTimeLogs().stream()
                .filter(timeLog -> timeLog.getId() == null)
                .forEach(timeLog -> {
                    timeLog.setActivity(activity);
                    timeLogRepository.save(timeLog);
                });
        return modelMapper.map(jpaRepository.save(activity), ActivityDto.class);
    }

    public ActivityDto addTimeLog(Long id, TimeLogDto timeLogDto) {
        Activity activity = getEntity(id);
        TimeLog timeLog = modelMapper.map(timeLogDto, TimeLog.class);
        timeLog.setActivity(activity);
        timeLogRepository.save(timeLog);
        activity.getTimeLogs().add(timeLog);
        return modelMapper.map(jpaRepository.save(activity), ActivityDto.class);
    }
}
