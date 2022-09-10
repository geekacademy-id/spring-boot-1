package com.javan.helloworldweb.models.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NewsDto {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    @JsonAlias("category_id")
    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @JsonAlias("author_id")
    @NotNull(message = "Author ID is required")
    private Long authorId;

    @JsonAlias("is_published")
    @NotNull(message = "Is Published is required")
    private Boolean isPublished;
}
