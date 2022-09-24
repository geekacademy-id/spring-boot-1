package com.example.springboot.models.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TagNewsDto {
    @JsonAlias("tag_id")
    @NotNull(message = "Tag ID is required")
    private Long tagId;

    @JsonAlias("news_id")
    @NotNull(message = "News ID is required")
    private Long newsId;
}
