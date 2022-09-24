package com.ichmal.trainingspringboot.crud.newsreport;

import com.ichmal.trainingspringboot.crud.models.News;
import lombok.Getter;

import java.util.Date;

@Getter
public class NewsReport {
    private final String title;
    private final String content;
    private final Date createdAt;
    private final Boolean isPublished;
    private final String authorName;

    public NewsReport(News news) {
        this.title = news.getTitle();
        this.content = news.getContent();
        this.createdAt = news.getCreateAt();
        this.isPublished = news.getIsPublished();
        this.authorName = news.getAuthor().getFullname();
    }
}
