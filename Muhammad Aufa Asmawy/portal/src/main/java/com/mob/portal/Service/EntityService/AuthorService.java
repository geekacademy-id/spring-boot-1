package com.mob.portal.Service;

import com.mob.portal.Entity.Author;
import com.mob.portal.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorService extends EntityService <AuthorRepository, Author, Long>{
//    @Autowired
//    private AuthorRepository authorRepository;
//
//    public List<Author> findAll(){
//        return (List<Author>) authorRepository.findAll();
//    }
//
//    public Author save(Author object){
//        return authorRepository.save(object);
//    }
//
//    public Optional<Author> findById(long id){
//        return authorRepository.findById(id);
//    }
//
//    public List<Author> deleteById(long id){
//        authorRepository.deleteById(id);
//        return authorRepository.findAll();
//    }
}
