package com.mob.portal.Api;

import com.mob.portal.Entity.Author;
import com.mob.portal.EntityDTO.AuthorDTO;
import com.mob.portal.Helper.ResponseBodyFormatter;
import com.mob.portal.Repository.AuthorRepository;
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
@RequestMapping("/api/v1.0/author/")
public class AuthorController {
    @Autowired
    private EntityService<AuthorRepository, Author, Long> authorService;

    @Autowired
    private ResponseBodyFormatter responseBodyFormatter;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<ResponseBodyFormatter> findAll(){
        List<Author> data = (List<Author>) authorService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                responseBodyFormatter.success("Success get all data", data)
        );
    }

    @PostMapping
    public ResponseEntity<ResponseBodyFormatter> save(@Valid @RequestBody AuthorDTO object, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    responseBodyFormatter.error(errors)
            );
        }

        Author data = authorService.save(modelMapper.map(object, Author.class));
        return ResponseEntity.status(HttpStatus.OK).body(
                responseBodyFormatter.success("Success add one data", data)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBodyFormatter> findById(@PathVariable(value = "id") long id){
        Optional<Author> target = authorService.findById(id);

        if(target.isPresent()){
            Author data = target.get();
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
    public ResponseEntity<ResponseBodyFormatter> updateById(@PathVariable(value = "id") long id, @Valid @RequestBody AuthorDTO object, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    responseBodyFormatter.error(errors)
            );
        }

        Optional<Author> target = authorService.findById(id);

        if(target.isPresent()){
            Author newObject = target.get();
            modelMapper.map(object, newObject);
            Author data = authorService.save(newObject);
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
        Optional<Author> target = authorService.findById(id);

        if(target.isPresent()){
            authorService.deleteById(id);
            List<Author> data = authorService.findAll();
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
