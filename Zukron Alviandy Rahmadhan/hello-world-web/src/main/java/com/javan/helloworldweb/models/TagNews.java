package com.javan.helloworldweb.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TagNews {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "news_id")
    private News news;
}
