package com.considlia.tictacbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class TimeLogDto implements Dto {
    private Long id;
    private OffsetDateTime dateTime;
    private String duration;
    private Boolean isReported;
    private Boolean isSubmitted;
}
