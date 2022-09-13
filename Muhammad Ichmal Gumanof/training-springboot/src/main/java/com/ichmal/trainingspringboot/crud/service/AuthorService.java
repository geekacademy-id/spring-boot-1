package com.ichmal.trainingspringboot.crud.service;

import com.ichmal.trainingspringboot.crud.NotFoundException;
import com.ichmal.trainingspringboot.crud.models.Author;
import com.ichmal.trainingspringboot.crud.models.dto.AuthorDto;
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

    public Author create(AuthorDto dto) {
        Author author = new Author();
        author.setFullname(dto.getFullname());
        author.setEmail(dto.getEmail());

        return authorRepository.save(author);
    }

    public Author update(Long id, AuthorDto dto) throws NotFoundException {
        Author author = authorRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found"));

        author.setFullname(dto.getFullname());
        author.setEmail(dto.getEmail());
        return authorRepository.save(author);
    }

    public void delete(Long id) throws NotFoundException {
        Author author = authorRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found"));

        authorRepository.delete(author);
    }
}
