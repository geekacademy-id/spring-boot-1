package com.ichmal.trainingspringboot.crud.controller;

import com.ichmal.trainingspringboot.crud.NotFoundException;
import com.ichmal.trainingspringboot.crud.models.Response;
import com.ichmal.trainingspringboot.crud.models.Tag;
import com.ichmal.trainingspringboot.crud.models.dto.TagDto;
import com.ichmal.trainingspringboot.crud.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping
    public Response all() {
        return new Response(HttpStatus.OK, "Success list all tag", tagService.list());
    }

    @PostMapping
    public Response create(@Valid @RequestBody TagDto tagDto) {
        Tag tag = tagService.create(tagDto);

        return new Response(HttpStatus.CREATED, "Success create tag", tag);
    }

    @PutMapping(value = "/{id}")
    public Response update(@PathVariable("id") Long id, @Valid @RequestBody TagDto tagDto) throws NotFoundException {
        Tag tag = tagService.update(id, tagDto);

        return new Response(HttpStatus.OK, "Success update tag", tag);
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@PathVariable("id") Long id) throws NotFoundException{
        tagService.delete(id);

        return new Response(HttpStatus.OK, "Success delete tag", null);
    }
}
