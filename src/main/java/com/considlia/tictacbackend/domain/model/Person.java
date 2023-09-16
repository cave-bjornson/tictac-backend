package com.considlia.tictacbackend.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
@NoArgsConstructor
@SuperBuilder(setterPrefix = "with")
@Getter
@Setter
@ToString(callSuper = true)
public class Person extends AbstractEntity {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phone;
    @Email
    @NotBlank
    private String email;
    @OneToOne
    private Address address;
}
