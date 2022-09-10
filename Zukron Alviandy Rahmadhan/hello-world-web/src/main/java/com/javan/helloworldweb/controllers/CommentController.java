package com.javan.helloworldweb.controllers;

import com.javan.helloworldweb.exceptions.NotFoundException;
import com.javan.helloworldweb.models.Comment;
import com.javan.helloworldweb.models.Response;
import com.javan.helloworldweb.models.dto.CommentDto;
import com.javan.helloworldweb.models.interfaces.CommentCreate;
import com.javan.helloworldweb.models.interfaces.CommentUpdate;
import com.javan.helloworldweb.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public Response all() {
        List<Comment> commentList = commentService.list();

        return new Response(HttpStatus.OK, "Success get all comment", commentList);
    }

    @PostMapping
    public Response create(@Validated(CommentCreate.class) @RequestBody CommentDto commentDto) throws NotFoundException {
        Comment comment = commentService.create(commentDto);

        return new Response(HttpStatus.CREATED, "Success create comment", comment);
    }

    @PutMapping(value = "/{id}")
    public Response update(@PathVariable("id") Long id, @Validated(CommentUpdate.class) @RequestBody CommentDto commentDto) throws NotFoundException {
        Comment comment = commentService.update(id, commentDto);

        return new Response(HttpStatus.OK, "Success update comment", comment);
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@PathVariable("id") Long id) throws NotFoundException {
        commentService.delete(id);

        return new Response(HttpStatus.OK, "Success delete comment", null);
    }
}
