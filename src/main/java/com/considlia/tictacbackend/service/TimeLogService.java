package com.considlia.tictacbackend.service;

import com.considlia.tictacbackend.domain.model.Activity;
import com.considlia.tictacbackend.domain.model.TimeLog;
import com.considlia.tictacbackend.domain.repository.TimeLogRepository;
import com.considlia.tictacbackend.dto.TimeLogDto;
import com.considlia.tictacbackend.util.EntityDtoMapper;
import org.springframework.stereotype.Service;

@Service
public class TimeLogService extends CrudService<TimeLog, Long, TimeLogDto>{

    final TimeLogRepository timeLogRepository;
    final ActivityService activityService;

    public TimeLogService(TimeLogRepository timeLogRepository, EntityDtoMapper entityDtoMapper, ActivityService activityService) {
        super(timeLogRepository, TimeLog.class, TimeLogDto.class, entityDtoMapper);
        this.timeLogRepository = timeLogRepository;
        this.activityService = activityService;
    }

    public void deleteTimeLog(Long id) {
        timeLogRepository.deleteById(id);

    }

    public TimeLogDto saveTimeLog(Long activityId, TimeLogDto timeLogDto) {
        Activity activity = activityService.getEntity(activityId);
        TimeLog timeLog = modelMapper.map(timeLogDto, TimeLog.class);
        timeLog.setActivity(activity);
        return modelMapper.map(timeLogRepository.save(timeLog), TimeLogDto.class);
    }
}

