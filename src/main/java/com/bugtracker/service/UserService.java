package com.bugtracker.service;

import com.bugtracker.dto.UserRequestDTO;
import com.bugtracker.dto.UserResponseDTO;
import com.bugtracker.exception.UserNotFoundException;
import com.bugtracker.model.User;
import com.bugtracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

@Service
public class UserService {

    private final UserRepository ur;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository ur,
                       PasswordEncoder passwordEncoder) {

        this.ur = ur;
        this.passwordEncoder = passwordEncoder;
    }

    // Create User
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        // DTO -> Entity
        User user = new User();

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setRole(userRequestDTO.getRole());

        // Save
        User savedUser = ur.save(user);

        // Entity -> DTO
        UserResponseDTO response = new UserResponseDTO();

        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());

        return response;
    }

    // Get All Users
    public List<UserResponseDTO> getAllUsers() {

        List<User> users = ur.findAll();

        return users.stream().map(user -> {

            UserResponseDTO response = new UserResponseDTO();

            response.setId(user.getId());
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setRole(user.getRole());

            return response;

        }).toList();
    }

    // Update User
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {

        User existing = ur.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id " + id));

        existing.setName(userRequestDTO.getName());
        existing.setEmail(userRequestDTO.getEmail());
        existing.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        existing.setRole(userRequestDTO.getRole());

        User updatedUser = ur.save(existing);

        UserResponseDTO response = new UserResponseDTO();

        response.setId(updatedUser.getId());
        response.setName(updatedUser.getName());
        response.setEmail(updatedUser.getEmail());
        response.setRole(updatedUser.getRole());

        return response;
    }
    // Delete User
    public String deleteUser(Long id) {

        User user = ur.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id " + id));

        ur.delete(user);

        return "User deleted successfully";
    }
}