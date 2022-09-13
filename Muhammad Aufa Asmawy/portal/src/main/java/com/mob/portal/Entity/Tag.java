package com.mob.portal.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tag")
//@Getter
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
public class Tag implements Serializable {

    @Getter
    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
//    @Column(unique = true)
    @NotNull(message = "tag name is required")
    private String name;

    @ManyToMany(mappedBy = "tagSet")
//    @JsonBackReference
    @Setter
    private Set<News> newsSet;
}
