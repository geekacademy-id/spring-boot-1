package com.javan.helloworldweb.controllers;

import com.javan.helloworldweb.config.annotations.NeedToken;
import com.javan.helloworldweb.exceptions.NotFoundException;
import com.javan.helloworldweb.models.Author;
import com.javan.helloworldweb.models.Response;
import com.javan.helloworldweb.models.dto.AuthorDto;
import com.javan.helloworldweb.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/author")
@NeedToken
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Response all() {
        return new Response(HttpStatus.OK, "Success list all author", authorService.list());
    }

    @PostMapping
    public Response create(@Valid @RequestBody AuthorDto authorDto) {
        Author author = authorService.create(authorDto);

        return new Response(HttpStatus.CREATED, "Success create author", author);
    }

    @PutMapping(value = "/{id}")
    public Response update(@PathVariable("id") Long id, @Valid @RequestBody AuthorDto authorDto) throws NotFoundException {
        Author author = authorService.update(id, authorDto);

        return new Response(HttpStatus.OK, "Success create author", author);
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@PathVariable("id") Long id) throws NotFoundException {
        authorService.delete(id);

        return new Response(HttpStatus.OK, "Success create author", null);
    }
}
