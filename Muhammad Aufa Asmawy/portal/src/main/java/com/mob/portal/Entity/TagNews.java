package com.mob.portal.Entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tag_news")
@Getter
public class TagNews {
    @Id
    private Long tag_id;
//    @Id
//    private Long news_id;
}
