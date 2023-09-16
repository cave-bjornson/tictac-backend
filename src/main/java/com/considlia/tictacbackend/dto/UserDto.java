package com.considlia.tictacbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class UserDto implements Dto {

    private Long id;
    @Schema(description = "The users first name", example = "Bob")
    private String firstName;
    private String lastName;
    private String phone;
    private String username;
    private String email;
    private String role;
}
