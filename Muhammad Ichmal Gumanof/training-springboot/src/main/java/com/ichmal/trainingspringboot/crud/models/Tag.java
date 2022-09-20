package com.ichmal.trainingspringboot.crud.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;
}
