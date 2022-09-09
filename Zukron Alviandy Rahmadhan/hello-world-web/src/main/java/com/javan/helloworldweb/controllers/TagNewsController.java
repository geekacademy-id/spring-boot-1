package com.javan.helloworldweb.controllers;

import com.javan.helloworldweb.exceptions.NotFoundException;
import com.javan.helloworldweb.models.Response;
import com.javan.helloworldweb.models.TagNews;
import com.javan.helloworldweb.models.dto.TagNewsDto;
import com.javan.helloworldweb.services.TagNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tag-news")
public class TagNewsController {
    @Autowired
    private TagNewsService tagNewsService;

    @GetMapping
    public Response all() {
        List<TagNews> tagNewsList = tagNewsService.list();

        return new Response(HttpStatus.OK, "Success get all tag news", tagNewsList);
    }

    @PostMapping
    public Response create(@Valid @RequestBody TagNewsDto tagNewsDto) throws NotFoundException {
        TagNews tagNews = tagNewsService.create(tagNewsDto);

        return new Response(HttpStatus.CREATED, "Success create tag news", tagNews);
    }

    @PutMapping(value = "/{id}")
    public Response update(@PathVariable Long id, @Valid @RequestBody TagNewsDto tagNewsDto) throws NotFoundException {
        TagNews tagNews = tagNewsService.update(id, tagNewsDto);

        return new Response(HttpStatus.OK, "Success update tag news", tagNews);
    }

    @DeleteMapping(value = "/{id}")
    public Response delete( @PathVariable Long id) throws NotFoundException {
        tagNewsService.delete(id);

        return new Response(HttpStatus.OK, "Success delete tag news", null);
    }
}
