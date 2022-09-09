package com.javan.helloworldweb.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "comentator_name")
    private String commentatorName;

    private String content;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "news_id")
    private News news;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "is_banned")
    private Boolean isBanned;

}
