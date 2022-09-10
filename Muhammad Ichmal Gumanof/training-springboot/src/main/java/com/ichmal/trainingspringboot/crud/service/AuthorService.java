package com.ichmal.trainingspringboot.crud.service;

import com.ichmal.trainingspringboot.crud.models.Author;
import com.ichmal.trainingspringboot.crud.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> list() {
        return authorRepository.findAll();
    }
    
    public void findId(Long id){
        authorRepository.findById(id);
    }

    public void create(Author author) {
        authorRepository.save(author);
    }

    public void update(Long id, Author author) {
        authorRepository.findById(id).ifPresent(item -> {
            item.setFullname(author.getFullname());
            item.setEmail(author.getEmail());

            authorRepository.save(item);
        });
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
