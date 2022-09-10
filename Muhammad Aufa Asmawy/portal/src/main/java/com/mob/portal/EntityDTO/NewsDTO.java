package com.mob.portal.EntityDTO;

import com.mob.portal.Entity.Author;
import com.mob.portal.Entity.Category;
import com.mob.portal.Entity.Tag;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class NewsDTO {
    @Setter
    @NotNull(message = "news title is required")
    private String title;

    @Setter
    @Column(columnDefinition = "text")
    @NotNull(message = "news content is required")
    private String content;

    @Setter
    private boolean published = true;

    @Setter
    @NotNull(message = "category_id is required")
    private Category category;

    @Setter
    @NotNull(message = "author_id is required")
    private Author author;

    @Setter
    private ArrayList<Tag> tagSet;
}
