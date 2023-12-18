package org.oskkar.crudapirest.service;

import org.oskkar.crudapirest.dto.UserDto;
import org.oskkar.crudapirest.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    List<UserDto> getAllUsers();
    UserDto getUserById(Integer id);
    UserDto updateUserById(UserDto user);
    void deleteUserById(Integer id);
}
