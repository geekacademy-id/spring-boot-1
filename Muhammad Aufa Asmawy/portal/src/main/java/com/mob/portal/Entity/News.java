package com.mob.portal.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "news")
@Getter
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
public class News implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull(message = "news title is required")
    private String title;

    @Setter
    @Column(columnDefinition = "text")
    @NotNull(message = "news content is required")
    private String content;

    private final LocalDateTime created_at = LocalDateTime.now();

    @Setter
    private boolean published;

    @ManyToOne
    @Setter
    @NotNull(message = "category_id is required")
    private Category category;

    @ManyToOne
    @Setter
    @NotNull(message = "author_id is required")
    private Author author;

    @ManyToMany
    @Setter
    @JoinTable(name = "tag_news",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
//    @JsonManagedReference
    private Set<Tag> tagSet;
}
