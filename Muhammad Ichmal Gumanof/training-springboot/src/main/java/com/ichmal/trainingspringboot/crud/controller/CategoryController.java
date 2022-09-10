package com.ichmal.trainingspringboot.crud.controller;

import com.ichmal.trainingspringboot.crud.ResponseFormatter;
import com.ichmal.trainingspringboot.crud.models.Category;
import com.ichmal.trainingspringboot.crud.models.Response;
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
    public Response create(@Valid @RequestBody Category category) {
        categoryService.create(category);

        return new Response(HttpStatus.CREATED, "Success create category", category);
    }

    @GetMapping("/{id}")
    public ResponseFormatter finAuthorById(@PathVariable(value = "id") long id){
        Optional<Category> target = categoryRepository.findById(id);

        if (target.isPresent()){
            Category data = target.get();
            return responseFormatter.generate(200, "Success search data by id: " + id, data);
        } else {
            return responseFormatter.generate(400, "failed to get data by id" + id);
        }

    }

    @PostMapping(value = "/{id}")
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
