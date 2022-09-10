package com.mob.portal.Service;

import com.mob.portal.Entity.Category;
import com.mob.portal.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryService extends EntityService <CategoryRepository, Category, Long>{
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    public List<Category> findAll(){
//        return (List<Category>) categoryRepository.findAll();
//    }
//
//    public Category save(Category object){
//        return categoryRepository.save(object);
//    }
//
//    public Optional<Category> findById(long id){
//        return categoryRepository.findById(id);
//    }
//
//    public List<Category> deleteById(long id){
//        categoryRepository.deleteById(id);
//        return categoryRepository.findAll();
//    }
}
