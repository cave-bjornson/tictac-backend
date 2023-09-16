package com.considlia.tictacbackend.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@NoArgsConstructor
@SuperBuilder(setterPrefix = "with")
@Getter
@Setter
@ToString
public class Customer extends Person {
    @OneToMany(mappedBy = "customer")
    @Singular
    private Set<Project> projects;
}
