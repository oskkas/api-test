package org.oskkar.crudapirest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.oskkar.crudapirest.dto.UserDto;
import org.oskkar.crudapirest.entity.User;
import org.oskkar.crudapirest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Users",
        description = "Users API"
)
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class Controller {
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        UserDto userSaved = userService.createUser(user);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Integer userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable("id") Integer userId, @Valid @RequestBody UserDto user) {
        user.setId(userId);
        UserDto userUpdated = userService.updateUserById(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Integer userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
