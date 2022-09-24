package com.javan.helloworldweb.controllers;

import com.javan.helloworldweb.config.annotations.NeedToken;
import com.javan.helloworldweb.exceptions.NotFoundException;
import com.javan.helloworldweb.models.Category;
import com.javan.helloworldweb.models.Response;
import com.javan.helloworldweb.models.dto.CategoryDto;
import com.javan.helloworldweb.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
@NeedToken
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Response all() {
        List<Category> categoryList = categoryService.list();

        return new Response(HttpStatus.OK, "Success list all category", categoryList);
    }

    @PostMapping
    public Response create(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.create(categoryDto);

        return new Response(HttpStatus.CREATED, "Success create author", category);
    }

    @PutMapping(value = "/{id}")
    public Response update(@PathVariable("id") Long id, @Valid @RequestBody CategoryDto categoryDto) throws NotFoundException {
        Category category = categoryService.update(id, categoryDto);

        return new Response(HttpStatus.OK, "Success create category", category);
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@PathVariable("id") Long id) throws NotFoundException {
        categoryService.delete(id);

        return new Response(HttpStatus.OK, "Success create category", null);
    }
}
