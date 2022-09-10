package com.ichmal.trainingspringboot.crud.controller;

import com.ichmal.trainingspringboot.crud.ResponseFormatter;
import com.ichmal.trainingspringboot.crud.models.Author;
import com.ichmal.trainingspringboot.crud.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    ResponseFormatter responseFormatter;

    @GetMapping
    public ResponseFormatter findALlAuthor() {
        List<Author> data = authorRepository.findAll();
        return responseFormatter.generate(200, "Success get all data", data);
    }

    @PostMapping
    public ResponseFormatter saveAuthor(@Valid @RequestBody Author author) {
        Author data = authorRepository.save(author);

        return responseFormatter.generate(200, "Success add one data", data);
    }

    @GetMapping("/{id}")
    public ResponseFormatter finAuthorById(@PathVariable(value = "id") long id){
        Optional<Author> target = authorRepository.findById(id);

        if (target.isPresent()){
            Author data = target.get();
            return responseFormatter.generate(200, "Success search data by id: " + id, data);
        } else {
            return responseFormatter.generate(400, "failed to get data by id" + id);
        }

    }

    @PostMapping(value = "/{id}")
    public ResponseFormatter updateAuthorById(@PathVariable("id") Long id, @Valid @RequestBody Author author) {
        Optional<Author> target = authorRepository.findById(id);

        if (target.isPresent()){
            Author object = target.get();
            object.setFullname(author.getFullname());
            object.setEmail(author.getEmail());
            Author data = authorRepository.save(object);
            return responseFormatter.generate(200, "Success update data by id: "+id, data);
        } else {
            return responseFormatter.generate(400, "failed to update data by id" + id);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseFormatter delete(@PathVariable("id") Long id) {
        Optional<Author> target = authorRepository.findById(id);

        if(target.isPresent()) {
            authorRepository.deleteById(id);
            List<Author> data = authorRepository.findAll();
            return responseFormatter.generate(200, "Success delete data by id "+id, data);
        } else {
            return responseFormatter.generate(400, "failed to delete data by id" + id);
        }
    }
}
