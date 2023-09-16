package com.considlia.tictacbackend.api;


import com.considlia.tictacbackend.domain.model.TimeLog;
import com.considlia.tictacbackend.dto.ProjectDto;
import com.considlia.tictacbackend.dto.TimeLogDto;
import com.considlia.tictacbackend.service.TimeLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = BaseController.PATH + "/timelog")
@Tag(
        name = "Timelogs API",
        description = "A simple API for managing timelogs"
)
public class TimeLogController {

    final TimeLogService timeLogService;

    public TimeLogController(TimeLogService timeLogService) {
        this.timeLogService = timeLogService;
    }

    @Operation(summary = "Get all available timelogs")
    @GetMapping
    ResponseEntity<List<TimeLogDto>> getAllTimeLogs() {
        return ResponseEntity.ok(timeLogService.getAll());
    }

    @Operation(summary = "Delete a timelog")
    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> deleteTimeLog(@PathVariable Long id) {
        timeLogService.deleteTimeLog(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Save a timelog")
    @PostMapping(path = "/{activityId}")
    ResponseEntity<TimeLogDto> saveTimeLog(@PathVariable Long activityId, @RequestBody TimeLogDto timeLogDto) {
        return ResponseEntity.ok(timeLogService.saveTimeLog(activityId, timeLogDto));
    }
}
