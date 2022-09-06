package com.mob.portal.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "news")
@Getter
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private Long category_id;
    private Long author_id;
    private Calendar created_at;
    @Setter
    private boolean is_published;

}
