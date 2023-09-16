package com.considlia.tictacbackend.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@NoArgsConstructor
@SuperBuilder(setterPrefix = "with")
@Getter
@Setter
@ToString
public class Activity extends AbstractEntity {
    @NotBlank
    private String title;
    //    @DurationMin
//    private Duration duration;
    @PositiveOrZero
    private BigDecimal costPerHour;
//    private LocalDateTime dateTime;
//    private boolean isReported;

    @ManyToOne
    @ToString.Exclude
    private Project project;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<TimeLog> timeLogs;

//    public void setDuration(Duration duration) {
//        this.duration = duration;
//    }
//
//    public void setDurationFromString(String durationAsString) {
//        this.duration = Duration.parse(durationAsString);
//    }
}
