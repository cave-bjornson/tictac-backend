package com.considlia.tictacbackend.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@NoArgsConstructor
@SuperBuilder(setterPrefix = "with")
@Getter
@Setter
@ToString(callSuper = true)
public class Project extends AbstractEntity {

    @NotBlank
    private String title;

    @ManyToOne
    @ToString.Exclude
    private Customer customer;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
    @Singular
    @ToString.Exclude
    private Set<User> users;

    @OneToMany(mappedBy = "project", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Activity> activities;
}
