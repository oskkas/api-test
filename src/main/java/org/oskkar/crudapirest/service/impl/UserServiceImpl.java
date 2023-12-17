package org.oskkar.crudapirest.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.oskkar.crudapirest.dto.UserDto;
import org.oskkar.crudapirest.entity.User;
import org.oskkar.crudapirest.exception.EmailAlreadyExistsException;
import org.oskkar.crudapirest.exception.ResourceNotFoundException;
import org.oskkar.crudapirest.mapper.AutoUserMapper;
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
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        //User user = UserMapper.toUser(userDto);
        //User user = modelMapper.map(userDto, User.class);

        Optional<User> findByEmail = userRepository.findByEmail(userDto.getEmail());
        if (findByEmail.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = AutoUserMapper.MAPPER.toUser(userDto);

        User savedUser = userRepository.save(user);

        //UserDto userDtoSaved = modelMapper.map(savedUser, UserDto.class);

        return AutoUserMapper.MAPPER.toUserDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        /*return userList.stream().map(UserMapper::toUserDto)
                .collect(Collectors.toList());*/
        /*return userList.stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());*/
        return userList.stream().map(AutoUserMapper.MAPPER::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        //UserDto userDto = modelMapper.map(user, UserDto.class);
        return AutoUserMapper.MAPPER.toUserDto(user);
    }

    @Override
    public UserDto updateUserById(User user) {
        User userToUpdate = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userRepository.save(userToUpdate);
        //return modelMapper.map(userToUpdate, UserDto.class);
        return AutoUserMapper.MAPPER.toUserDto(userToUpdate);
    }

    @Override
    public void deleteUserById(Integer userId) {
        User userToUpdate = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }


}
