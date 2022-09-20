package com.ichmal.trainingspringboot.crud.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Date createAt;
    @Column(name = "is_published")
    private Boolean isPublished;
}
