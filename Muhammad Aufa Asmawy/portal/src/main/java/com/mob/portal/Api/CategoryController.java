package com.mob.portal.Api;

import com.mob.portal.Entity.Category;
import com.mob.portal.EntityDTO.CategoryDTO;
import com.mob.portal.Helper.ResponseBodyFormatter;
import com.mob.portal.Repository.CategoryRepository;
import com.mob.portal.Service.EntityService.EntityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/category/")
public class CategoryController {
    @Autowired
    private EntityService<CategoryRepository, Category, Long> categoryService;

    @Autowired
    ResponseBodyFormatter responseBodyFormatter;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<ResponseBodyFormatter> findAll(){
        List<Category> data = (List<Category>) categoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                responseBodyFormatter.success("Success get all data", data)
        );
    }

    @PostMapping
    public ResponseEntity<ResponseBodyFormatter> save(@Valid @RequestBody CategoryDTO object, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    responseBodyFormatter.error(errors)
            );
        }

        Category data = categoryService.save(modelMapper.map(object, Category.class));
        return ResponseEntity.status(HttpStatus.OK).body(
                responseBodyFormatter.success("Success add one data", data)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBodyFormatter> findById(@PathVariable(value = "id") long id){
        Optional<Category> target = categoryService.findById(id);

        if(target.isPresent()){
            Category data = target.get();
            return ResponseEntity.status(HttpStatus.OK).body(
                    responseBodyFormatter.success("Success get data by id: " + id, data)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    responseBodyFormatter.error("Data by id: " + id + " not found")
            );
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseBodyFormatter> updateById(@PathVariable(value = "id") long id, @Valid @RequestBody CategoryDTO object, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    responseBodyFormatter.error(errors)
            );
        }

        Optional<Category> target = categoryService.findById(id);

        if(target.isPresent()){
            Category newObject = target.get();
            modelMapper.map(object, newObject);
            Category data = categoryService.save(newObject);
            return ResponseEntity.status(HttpStatus.OK).body(
                    responseBodyFormatter.success("Success update data by id: " + id, data)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    responseBodyFormatter.error("Data by id: " + id + " not found")
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyFormatter> deleteById(@PathVariable(value = "id") long id){
        Optional<Category> target = categoryService.findById(id);

        if(target.isPresent()){
            categoryService.deleteById(id);
            List<Category> data = categoryService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(
                    responseBodyFormatter.success("Success delete data by id: " + id, data)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    responseBodyFormatter.error("Data by id: " + id + " not found")
            );
        }
    }
}
