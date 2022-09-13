package com.ichmal.trainingspringboot.crud.models.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class AuthorDto {
    @NotBlank(message = "Full name is required")
    private String fullname;
    @NotBlank(message = "Email is required")
    @Email
    private String email;
}
