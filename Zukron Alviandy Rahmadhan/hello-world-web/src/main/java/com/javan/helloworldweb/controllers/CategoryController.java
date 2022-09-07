package com.javan.helloworldweb.controllers;

import com.javan.helloworldweb.models.Category;
import com.javan.helloworldweb.models.Response;
import com.javan.helloworldweb.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Response all() {
        List<Category> categoryList = categoryService.list();

        return new Response(HttpStatus.OK, "Success list all category", categoryList);
    }

    @PostMapping
    public Response create(@Valid @RequestBody Category category) {
        categoryService.create(category);

        return new Response(HttpStatus.CREATED, "Success create author", category);
    }

    @PutMapping(value = "/{id}")
    public Response update(@PathVariable("id") Long id, @Valid @RequestBody Category category) {
        categoryService.update(id, category);

        return new Response(HttpStatus.OK, "Success create category", category);
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@PathVariable("id") Long id) {
        categoryService.delete(id);

        return new Response(HttpStatus.OK, "Success create category", null);
    }
}
