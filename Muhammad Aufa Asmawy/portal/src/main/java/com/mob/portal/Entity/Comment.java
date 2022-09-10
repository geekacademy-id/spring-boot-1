package com.mob.portal.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
@Table(name = "comment")
@Getter
public class Comment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull(message = "commentator name is required")
    private String commentator_name;

    @Setter
    @Column(columnDefinition = "text")
    @NotNull(message = "content is required")
    private String content;

    private final LocalDateTime created_at = LocalDateTime.now();

    @Setter
    private boolean banned;

    @ManyToOne
    @Setter
    @NotNull(message = "news_id is required")
    private News news;
}
