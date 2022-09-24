package com.example.springboot.controller;

import com.example.springboot.NotFoundException;
import com.example.springboot.models.Category;
import com.example.springboot.models.Response;
import com.example.springboot.models.dto.CategoryDto;
import com.example.springboot.repository.CategoryRepository;
import com.example.springboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public Response all(){
        return new Response(HttpStatus.OK, "Success list all category", categoryService.list());
    }

    @PostMapping
    public Response create(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.create(categoryDto);

        return new Response(HttpStatus.CREATED, "Success create author", category);
    }


    @PostMapping(value = "/{id}")
    public Response update(@PathVariable("id") Long id, @Valid @RequestBody CategoryDto categoryDto) throws NotFoundException {
        Category category = categoryService.update(id, categoryDto);

        return new Response(HttpStatus.OK, "Success update category", category);
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@PathVariable("id") Long id) throws NotFoundException{
        categoryService.delete(id);

        return new Response(HttpStatus.OK, "Success delete category", null);
    }
}
