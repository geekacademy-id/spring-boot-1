package com.javan.helloworldweb.services;

import com.javan.helloworldweb.exceptions.NotFoundException;
import com.javan.helloworldweb.models.Author;
import com.javan.helloworldweb.models.dto.AuthorDto;
import com.javan.helloworldweb.repositories.AuthorRepository;
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
