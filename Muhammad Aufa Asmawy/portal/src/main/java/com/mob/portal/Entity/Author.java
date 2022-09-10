package com.mob.portal.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "author")
@Getter
public class Author implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @NotNull(message = "author fullName is required")
    private String fullName;

    @Setter
//    @Column(unique = true)
    @Email
    @NotNull(message = "author email is required")
    private String email;
}
