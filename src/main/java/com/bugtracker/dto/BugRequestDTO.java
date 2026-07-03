package com.bugtracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bugtracker.enums.Priority;
import com.bugtracker.enums.Status;

import jakarta.validation.constraints.NotNull;

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

    @NotNull(message = "Priority cannot be empty")
    private Priority priority;

    @NotNull(message = "Status cannot be empty")
    private Status status;
}
