package com.considlia.tictacbackend.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@NoArgsConstructor
@SuperBuilder(setterPrefix = "with")
@Getter
@Setter
@ToString(callSuper = true)
public class User extends Person {
    @NotBlank
    private String username;
    @Length(min = 6, message = "needs to be at least 6 characters")
    private String password;
    @NotBlank
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @Singular
    private Set<Project> projects;
}
