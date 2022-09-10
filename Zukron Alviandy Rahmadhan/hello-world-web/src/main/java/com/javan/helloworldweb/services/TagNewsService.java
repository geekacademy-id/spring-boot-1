package com.javan.helloworldweb.services;

import com.javan.helloworldweb.exceptions.NotFoundException;
import com.javan.helloworldweb.models.News;
import com.javan.helloworldweb.models.Tag;
import com.javan.helloworldweb.models.TagNews;
import com.javan.helloworldweb.models.dto.TagNewsDto;
import com.javan.helloworldweb.repositories.NewsRepository;
import com.javan.helloworldweb.repositories.TagNewsRepository;
import com.javan.helloworldweb.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagNewsService {
    @Autowired
    private TagNewsRepository tagNewsRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private NewsRepository newsRepository;

    public List<TagNews> list() {
        return tagNewsRepository.findAll();
    }

    public TagNews create(TagNewsDto dto) throws NotFoundException {
        Tag tag = tagRepository
                .findById(dto.getTagId())
                .orElseThrow(()-> new NotFoundException("Tag not found"));

        News news = newsRepository
                .findById(dto.getNewsId())
                .orElseThrow(() -> new NotFoundException("News not found"));

        TagNews tagNews = new TagNews();
        tagNews.setTag(tag);
        tagNews.setNews(news);

        return tagNewsRepository.save(tagNews);
    }

    public TagNews update(Long id, TagNewsDto dto) throws NotFoundException {
        TagNews tagNews = tagNewsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Tag News not found"));

        Tag tag = tagRepository
                .findById(dto.getTagId())
                .orElseThrow(()-> new NotFoundException("Tag not found"));

        News news = newsRepository
                .findById(dto.getNewsId())
                .orElseThrow(() -> new NotFoundException("News not found"));

        tagNews.setTag(tag);
        tagNews.setNews(news);

        return tagNewsRepository.save(tagNews);
    }

    public void delete(Long id) throws NotFoundException {
        TagNews tagNews = tagNewsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Tag News not found"));

        tagNewsRepository.delete(tagNews);
    }
}
