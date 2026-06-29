package com.bugtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BugResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String priority;
    private String status;
}