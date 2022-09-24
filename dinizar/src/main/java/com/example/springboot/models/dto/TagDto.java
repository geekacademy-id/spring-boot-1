package com.example.springboot.models.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TagDto {
    @NotBlank(message = "Name is required")
    private String name;
}
