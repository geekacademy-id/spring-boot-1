package com.mob.portal.EntityDTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
public class CategoryDTO {
    @Setter
//    @Column(unique = true)
    @NotNull(message = "category name is required")
    private String name;

    @Setter
    @Column(columnDefinition = "text")
    private String description;
}
