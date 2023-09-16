package com.considlia.tictacbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class ActivityDto implements Dto {
    private Long id;
    private String title;
    //    private String duration;
//    private OffsetDateTime dateTime;
//    private boolean isReported;
    private Set<TimeLogDto> timeLogs;
}
