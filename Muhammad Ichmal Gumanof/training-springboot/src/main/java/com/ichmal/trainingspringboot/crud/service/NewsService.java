package com.ichmal.trainingspringboot.crud.service;

import com.ichmal.trainingspringboot.crud.NotFoundException;
import com.ichmal.trainingspringboot.crud.models.Author;
import com.ichmal.trainingspringboot.crud.models.Category;
import com.ichmal.trainingspringboot.crud.models.News;
import com.ichmal.trainingspringboot.crud.models.dto.NewsDto;
import com.ichmal.trainingspringboot.crud.repository.AuthorRepository;
import com.ichmal.trainingspringboot.crud.repository.CategoryRepository;
import com.ichmal.trainingspringboot.crud.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public List<News> list() {
        return newsRepository.findAll();
    }

    public News create(NewsDto dto) throws NotFoundException {
        Category category = categoryRepository
                .findById(dto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        Author author = authorRepository
                .findById(dto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found"));

        News news = new News();
        news.setTitle(dto.getTitle());
        news.setContent(dto.getContent());
        news.setCategory(category);
        news.setAuthor(author);
        news.setIsPublished(dto.getIsPublished());

        return newsRepository.save(news);
    }

    public News update(Long id, NewsDto dto) throws NotFoundException {
        News news = newsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("News not found"));

        Category category = categoryRepository
                .findById(dto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        Author author = authorRepository
                .findById(dto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found"));

        news.setTitle(dto.getTitle());
        news.setContent(dto.getContent());
        news.setCategory(category);
        news.setAuthor(author);
        news.setIsPublished(dto.getIsPublished());

        return newsRepository.save(news);
    }

    public void delete(Long id) throws NotFoundException {
        News news = newsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("News not found"));

        newsRepository.delete(news);
    }
}
