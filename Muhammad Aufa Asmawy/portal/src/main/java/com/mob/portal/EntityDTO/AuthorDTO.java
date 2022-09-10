package com.mob.portal.EntityDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class AuthorDTO {
    @Setter
    @NotNull(message = "author fullName is required")
    private String fullName;

    @Setter
//    @Column(unique = true)
    @Email
    @NotNull(message = "author email is required")
    private String email;
}
