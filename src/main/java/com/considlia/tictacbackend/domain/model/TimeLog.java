package com.considlia.tictacbackend.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(setterPrefix = "with")
@Embeddable
public class TimeLog extends AbstractEntity {

    private LocalDateTime localDateTime;
    private Duration duration;
    private Boolean isReported;
    private Boolean isSubmitted;
    @ManyToOne
    @ToString.Exclude
    private Activity activity;
}
