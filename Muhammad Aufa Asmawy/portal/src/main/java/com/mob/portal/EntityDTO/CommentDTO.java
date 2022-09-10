package com.mob.portal.EntityDTO;

import com.mob.portal.Entity.News;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Getter
public class CommentDTO {
    @Setter
    @NotNull(message = "commentator name is required")
    private String commentator_name;

    @Setter
    @Column(columnDefinition = "text")
    @NotNull(message = "content is required")
    private String content;

    @Setter
    private boolean banned = false;

    @Setter
    @NotNull(message = "news_id is required")
    private News news;
}
