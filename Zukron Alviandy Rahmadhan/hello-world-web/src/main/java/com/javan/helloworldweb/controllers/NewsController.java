package com.javan.helloworldweb.controllers;

import com.javan.helloworldweb.exceptions.NotFoundException;
import com.javan.helloworldweb.models.News;
import com.javan.helloworldweb.models.Response;
import com.javan.helloworldweb.models.dto.NewsDto;
import com.javan.helloworldweb.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping
    public Response all() {
        return new Response(HttpStatus.OK, "Success list all news", newsService.list());
    }

    @PostMapping
    public Response create(@Valid @RequestBody NewsDto newsDto) throws NotFoundException {
        News news = newsService.create(newsDto);

        return new Response(HttpStatus.CREATED, "Success create news", news);
    }

    @PutMapping(value = "/{id}")
    public Response update(@PathVariable("id") Long id, @Valid @RequestBody NewsDto newsDto) throws NotFoundException {
        News news = newsService.update(id, newsDto);

        return new Response(HttpStatus.OK, "Success create news", news);
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@PathVariable("id") Long id) {
        newsService.delete(id);

        return new Response(HttpStatus.OK, "Success create news", null);
    }
}
