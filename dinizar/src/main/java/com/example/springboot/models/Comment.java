package com.example.springboot.models;

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
    @Column(name = "comentator_name", nullable = false)
    private String comentatorName;
    private String content;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "news_id", nullable = false)
    private News news;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    @Column(name = "is_banned", nullable = false)
    private Boolean isBanned;
}
