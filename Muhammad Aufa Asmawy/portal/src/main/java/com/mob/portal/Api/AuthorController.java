package com.mob.portal.Api;

import com.mob.portal.Entity.Author;
import com.mob.portal.Helper.ResponseFormatter;
import com.mob.portal.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/author/")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    ResponseFormatter responseFormatter;

    @GetMapping
    public ResponseFormatter findAllAuthor(){
        List<Author> data = (List<Author>) authorRepository.findAll();
        return responseFormatter.generate(200, "Success get all data", data);
    }

    @PostMapping
    public ResponseFormatter saveAuthor(@Validated @RequestBody Author object){
        Author data = authorRepository.save(object);
        return responseFormatter.generate(200, "Success add one data", data);
    }

    @GetMapping("/{id}")
    public ResponseFormatter findAuthorById(@PathVariable(value = "id") long id){
        Optional<Author> target = authorRepository.findById(id);

        if(target.isPresent()){
            Author data = target.get();
            return responseFormatter.generate(200, "Success delete data by id: " + id, data);
        }else {
            return responseFormatter.generate(400, "Failed to get data by id: " + id);
        }
    }

    @PostMapping("/{id}")
    public ResponseFormatter updateAuthorById(@PathVariable(value = "id") long id, @RequestBody Author object){
        Optional<Author> target = authorRepository.findById(id);

        if(target.isPresent()){
            Author author = target.get();
            author.setFullName(object.getFullName());
            author.setEmail(object.getEmail());
            Author data = authorRepository.save(author);
            return responseFormatter.generate(200, "Success update data by id: " + id, data);
        }else{
            return responseFormatter.generate(400, "Failed to update data by id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseFormatter deleteUserById(@PathVariable(value = "id") long id){
        Optional<Author> target = authorRepository.findById(id);

        if(target.isPresent()){
            authorRepository.deleteById(id);
            List<Author> data = authorRepository.findAll();
            return responseFormatter.generate(200, "Success delete data by id: " + id, data);
        }else {
            return responseFormatter.generate(400, "Failed to delete data by id: " + id);
        }
    }
}
