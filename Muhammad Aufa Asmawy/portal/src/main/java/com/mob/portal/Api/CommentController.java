package com.mob.portal.Api;

import com.mob.portal.Entity.Comment;
import com.mob.portal.EntityDTO.CommentDTO;
import com.mob.portal.EntityDTO.NewsDTO;
import com.mob.portal.Helper.ResponseBodyFormatter;
import com.mob.portal.Repository.CommentRepository;
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
@RequestMapping("/api/v1.0/comment/")
public class CommentController {
    @Autowired
    private EntityService<CommentRepository, Comment, Long> commentService;

    @Autowired
    private ResponseBodyFormatter responseBodyFormatter;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<ResponseBodyFormatter> findAll(){
        List<Comment> data = (List<Comment>) commentService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                responseBodyFormatter.success("Success get all data", data)
        );
    }

    @PostMapping
    public ResponseEntity<ResponseBodyFormatter> save(@Valid @RequestBody CommentDTO object, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    responseBodyFormatter.error(errors)
            );
        }

        Comment data = commentService.save(modelMapper.map(object, Comment.class));
        return ResponseEntity.status(HttpStatus.OK).body(
                responseBodyFormatter.success("Success add one data", data)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBodyFormatter> findById(@PathVariable(value = "id") long id){
        Optional<Comment> target = commentService.findById(id);

        if(target.isPresent()){
            Comment data = target.get();
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
    public ResponseEntity<ResponseBodyFormatter> updateById(@PathVariable(value = "id") long id, @Valid @RequestBody CommentDTO object, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    responseBodyFormatter.error(errors)
            );
        }

        Optional<Comment> target = commentService.findById(id);

        if(target.isPresent()){
            Comment newObject = target.get();
            modelMapper.map(object, newObject);
            Comment data = commentService.save(newObject);
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
        Optional<Comment> target = commentService.findById(id);

        if(target.isPresent()){
            commentService.deleteById(id);
            List<Comment> data = commentService.findAll();
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
