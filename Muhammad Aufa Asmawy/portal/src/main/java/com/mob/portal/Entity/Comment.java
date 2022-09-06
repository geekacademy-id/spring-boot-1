package com.mob.portal.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "comment")
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commentator_name;
    @Setter
    private String content;
    private Long news_id;
    private Calendar created_at;
    @Setter
    private boolean is_banned;
}
