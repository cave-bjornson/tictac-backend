package com.considlia.tictacbackend.api;

import com.considlia.tictacbackend.domain.model.Activity;
import com.considlia.tictacbackend.dto.ActivityDto;
import com.considlia.tictacbackend.dto.TimeLogDto;
import com.considlia.tictacbackend.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = BaseController.PATH + "/activity")
@Tag(
        name = "Activities API",
        description = "A simple API for managing activities"
)

public class ActivityController extends BaseController<Activity, Long, ActivityDto> {

    final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        super(activityService);
        this.activityService = activityService;
    }

    @Operation(summary = "Get all available activities")
    @GetMapping
    ResponseEntity<List<ActivityDto>> getAllActivities() {
        return super.getAll();
    }

    @Operation(summary = "Get a single activity")
    @GetMapping("/{id}")
    ResponseEntity<ActivityDto> getActivityById(@PathVariable Long id) {
        return super.get(id);
    }

    @Operation(summary = "Update an existing activity")
    @PutMapping("/{id}")
    ResponseEntity<ActivityDto> updateActivity(@PathVariable Long id, @RequestBody ActivityDto activityDto) {
        return super.update(id, activityDto);
    }

    @Operation(summary = "Add TimeLog to activity")
    @PostMapping("/{id}/timelog")
    ResponseEntity<ActivityDto> addTimeLogToActivity(@PathVariable Long id, @RequestBody TimeLogDto timeLogDto) {
        return ResponseEntity.ok(activityService.addTimeLog(id, timeLogDto));
    }
}
