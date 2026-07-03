package com.bugtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bugtracker.enums.Priority;
import com.bugtracker.enums.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BugResponseDTO {

    private Long id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;
}