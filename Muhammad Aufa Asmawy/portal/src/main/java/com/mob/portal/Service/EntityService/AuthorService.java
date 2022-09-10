package com.mob.portal.Service.EntityService;

import com.mob.portal.Entity.Author;
import com.mob.portal.Repository.AuthorRepository;
import org.springframework.stereotype.Component;

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
