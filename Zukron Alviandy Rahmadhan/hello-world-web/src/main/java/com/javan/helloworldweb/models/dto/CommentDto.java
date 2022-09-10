package com.javan.helloworldweb.models.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.javan.helloworldweb.models.interfaces.CommentCreate;
import com.javan.helloworldweb.models.interfaces.CommentUpdate;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {
    @JsonAlias("commentator_name")
    @NotBlank(message = "Commentator Name is required", groups = {CommentCreate.class, CommentUpdate.class})
    private String commentatorName;

    @NotBlank(message = "Content is required", groups = {CommentCreate.class, CommentUpdate.class})
    private String content;

    @JsonAlias("news_id")
    @NotNull(message = "News ID is required", groups = CommentCreate.class)
    private Long newsId;

    @JsonAlias("is_banned")
    @NotNull(message = "Is Banned is required", groups = {CommentCreate.class, CommentUpdate.class})
    private Boolean isBanned;
}
