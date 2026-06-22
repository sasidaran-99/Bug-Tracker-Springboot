package com.bugtracker.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=5,max=10,message= " Title should be within 5 and 10 letters")
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Size(min=5,message = "The description should be above 5 letters")
    @NotBlank( message = "Description cannot be empty")
    private String description;

    @NotBlank( message ="Priority cannot be empty")
    private String priority;

    @NotBlank( message ="Status cannot be empty")
    private String status;

}
