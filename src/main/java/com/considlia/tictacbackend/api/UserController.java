package com.considlia.tictacbackend.api;

import com.considlia.tictacbackend.domain.model.User;
import com.considlia.tictacbackend.dto.UserDto;
import com.considlia.tictacbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(BaseController.PATH + "/user")
@Tag(
        name = "Users API",
        description = "A simple API for managing users"
)
public class UserController extends BaseController<User, Long, UserDto> {

    UserController(UserService userService) {
        super(userService);
    }

    @Operation(summary = "Get all available users", operationId = "getAllUsers")
    @GetMapping
    ResponseEntity<List<UserDto>> getAllUsers() {
        return super.getAll();
    }

    @Operation(summary = "Get a single user")
    @GetMapping(path = "{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return super.get(id);
    }
}
