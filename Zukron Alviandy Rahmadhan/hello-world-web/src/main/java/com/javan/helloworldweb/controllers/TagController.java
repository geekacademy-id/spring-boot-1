package com.javan.helloworldweb.controllers;

import com.javan.helloworldweb.exceptions.NotFoundException;
import com.javan.helloworldweb.models.Response;
import com.javan.helloworldweb.models.Tag;
import com.javan.helloworldweb.models.dto.TagDto;
import com.javan.helloworldweb.services.TagService;
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

        return new Response(HttpStatus.OK, "Success create tag", tag);
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@PathVariable("id") Long id) throws NotFoundException {
        tagService.delete(id);

        return new Response(HttpStatus.OK, "Success create tag", null);
    }
}
