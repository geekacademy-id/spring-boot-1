package com.example.springboot.service;

import com.example.springboot.NotFoundException;
import com.example.springboot.models.Author;
import com.example.springboot.models.Category;
import com.example.springboot.models.News;
import com.example.springboot.models.dto.NewsDto;
import com.example.springboot.repository.AuthorRepository;
import com.example.springboot.repository.CategoryRepository;
import com.example.springboot.repository.NewsRepository;
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

    public List<News> search(String keyword){
        return newsRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
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
