package com.javan.helloworldweb.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Full name is required")
    private String fullname;

    @NotBlank(message = "Email is required")
    @Email
    private String email;
}
