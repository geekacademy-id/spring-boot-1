package com.ichmal.trainingspringboot.crud.controller;

import com.ichmal.trainingspringboot.crud.NotFoundException;
import com.ichmal.trainingspringboot.crud.ResponseFormatter;
import com.ichmal.trainingspringboot.crud.models.Category;
import com.ichmal.trainingspringboot.crud.models.Response;
import com.ichmal.trainingspringboot.crud.models.dto.CategoryDto;
import com.ichmal.trainingspringboot.crud.repository.CategoryRepository;
import com.ichmal.trainingspringboot.crud.service.CategoryService;
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
    ResponseFormatter responseFormatter;
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
