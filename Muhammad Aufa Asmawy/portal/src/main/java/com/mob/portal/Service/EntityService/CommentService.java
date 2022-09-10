package com.mob.portal.Service;

import com.mob.portal.Entity.Comment;
import com.mob.portal.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CommentService extends EntityService <CommentRepository, Comment, Long>{
//    @Autowired
//    private CommentRepository commentRepository;
//
//    public List<Comment> findAll(){
//        return (List<Comment>) commentRepository.findAll();
//    }
//
//    public Comment save(Comment object){
//        return commentRepository.save(object);
//    }
//
//    public Optional<Comment> findById(long id){
//        return commentRepository.findById(id);
//    }
//
//    public List<Comment> deleteById(long id){
//        commentRepository.deleteById(id);
//        return commentRepository.findAll();
//    }
}
