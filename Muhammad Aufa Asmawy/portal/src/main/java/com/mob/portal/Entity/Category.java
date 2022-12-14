package com.mob.portal.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
//    @Column(unique = true)
    @NotNull(message = "category name is required")
    private String name;

    @Setter
    @Column(columnDefinition = "text")
    private String description;
}
