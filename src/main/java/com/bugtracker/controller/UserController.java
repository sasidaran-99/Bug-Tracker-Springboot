package com.bugtracker.controller;

import com.bugtracker.dto.UserRequestDTO;
import com.bugtracker.dto.UserResponseDTO;
import com.bugtracker.model.User;
import com.bugtracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService us;

    public UserController(UserService us) {
        this.us = us;
    }

    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return us.createUser(userRequestDTO);
    }
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return us.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO userRequestDTO) {

        return us.updateUser(id, userRequestDTO);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return us.deleteUser(id);
    }


}