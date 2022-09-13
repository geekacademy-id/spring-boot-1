package com.mob.portal.EntityDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
public class TagDTO {
    @Setter
//    @Column(unique = true)
    @NotNull(message = "tag name is required")
    private String name;
}
