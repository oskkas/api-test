package org.oskkar.crudapirest.service.impl;

import lombok.AllArgsConstructor;
import org.oskkar.crudapirest.dto.UserDto;
import org.oskkar.crudapirest.entity.User;
import org.oskkar.crudapirest.mapper.UserMapper;
import org.oskkar.crudapirest.repository.UserRepository;
import org.oskkar.crudapirest.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        User savedUser = userRepository.save(user);

        return UserMapper.toUserDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        UserDto userDto = UserMapper.toUserDto(optionalUser.get());
        return userDto;
    }

    @Override
    public UserDto updateUserById(User user) {
        User userToUpdate = userRepository.findById(user.getId()).get();
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userRepository.save(userToUpdate);
        return UserMapper.toUserDto(userToUpdate);
    }

    @Override
    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }


}
