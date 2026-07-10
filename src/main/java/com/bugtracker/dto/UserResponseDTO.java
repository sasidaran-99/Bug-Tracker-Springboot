package com.bugtracker.dto;

import com.bugtracker.enums.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;

    private String name;

    private String email;

    private Role role;
}