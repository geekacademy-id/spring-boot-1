package com.mob.portal.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tag")
@Getter
public class Tag implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
//    @Column(unique = true)
    @NotNull(message = "tag name is required")
    private String name;

    @ManyToMany(mappedBy = "tagSet")
    @JsonBackReference
    @Setter
    private Set<News> newsSet;
}
