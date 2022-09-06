package com.mob.portal.Api;

import com.mob.portal.Entity.Category;
import com.mob.portal.Helper.ResponseFormatter;
import com.mob.portal.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/category/")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    ResponseFormatter responseFormatter;

    @GetMapping
    public ResponseFormatter findAllAuthor(){
        List<Category> data = (List<Category>) categoryRepository.findAll();
        return responseFormatter.generate(200, "Success get all data", data);
    }

    @PostMapping
    public ResponseFormatter saveAuthor(@Validated @RequestBody Category object){
        Category data = categoryRepository.save(object);
        return responseFormatter.generate(200, "Success add one data", data);
    }

    @GetMapping("/{id}")
    public ResponseFormatter findAuthorById(@PathVariable(value = "id") long id){
        Optional<Category> target = categoryRepository.findById(id);

        if(target.isPresent()){
            Category data = target.get();
            return responseFormatter.generate(200, "Success delete data by id: " + id, data);
        }else {
            return responseFormatter.generate(400, "Failed to get data by id: " + id);
        }
    }

    @PostMapping("/{id}")
    public ResponseFormatter updateAuthorById(@PathVariable(value = "id") long id, @RequestBody Category object){
        Optional<Category> target = categoryRepository.findById(id);

        if(target.isPresent()){
            Category category = target.get();
            category.setName(object.getName());
            category.setDescription(object.getDescription());
            Category data = categoryRepository.save(category);
            return responseFormatter.generate(200, "Success update data by id: " + id, data);
        }else{
            return responseFormatter.generate(400, "Failed to update data by id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseFormatter deleteUserById(@PathVariable(value = "id") long id){
        Optional<Category> target = categoryRepository.findById(id);

        if(target.isPresent()){
            categoryRepository.deleteById(id);
            List<Category> data = categoryRepository.findAll();
            return responseFormatter.generate(200, "Success delete data by id: " + id, data);
        }else {
            return responseFormatter.generate(400, "Failed to delete data by id: " + id);
        }
    }
}
