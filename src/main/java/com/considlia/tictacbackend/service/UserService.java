package com.considlia.tictacbackend.service;

import com.considlia.tictacbackend.domain.model.User;
import com.considlia.tictacbackend.domain.repository.UserRepository;
import com.considlia.tictacbackend.dto.UserDto;
import com.considlia.tictacbackend.util.EntityDtoMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<User, Long, UserDto> {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository, EntityDtoMapper entityDtoMapper) {
        super(userRepository, User.class, UserDto.class, entityDtoMapper);
        this.userRepository = userRepository;
    }

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).get();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
