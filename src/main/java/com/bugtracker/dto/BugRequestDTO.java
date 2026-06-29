package com.bugtracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BugRequestDTO {
    @Size(min=5,max=100,message= " Title should be within 5 and 100 letters")
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Size(min=5,message = "The description should be above 5 letters")
    @NotBlank( message = "Description cannot be empty")
    private String description;

    @NotBlank( message ="Priority cannot be empty")
    @Pattern(
            regexp = "LOW|MEDIUM|HIGH",
            message = "Priority must be LOW, MEDIUM, or HIGH"
    )
    private String priority;

    @NotBlank( message ="Status cannot be empty")
    @Pattern(
            regexp = "OPEN|IN_PROGRESS|RESOLVED|CLOSED",
            message = "Status must be OPEN, IN_PROGRESS, RESOLVED, or CLOSED"
    )
    private String status;
}
