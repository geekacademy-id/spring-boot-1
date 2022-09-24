package com.example.springboot.controller;

import com.example.springboot.NotFoundException;
import com.example.springboot.models.Author;
import com.example.springboot.models.Response;
import com.example.springboot.models.dto.AuthorDto;
import com.example.springboot.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/author")
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

        return new Response(HttpStatus.OK, "Success update author", author);
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@PathVariable("id") Long id) throws NotFoundException {
        authorService.delete(id);

        return new Response(HttpStatus.OK, "Success delete author", null);
    }
}
