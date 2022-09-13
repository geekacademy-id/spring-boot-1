package com.ichmal.trainingspringboot.crud.service;

import com.ichmal.trainingspringboot.crud.NotFoundException;
import com.ichmal.trainingspringboot.crud.models.Author;
import com.ichmal.trainingspringboot.crud.models.Category;
import com.ichmal.trainingspringboot.crud.models.dto.CategoryDto;
import com.ichmal.trainingspringboot.crud.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> list(){
        return categoryRepository.findAll();
    }

    public void findId(Long id){
        categoryRepository.findById(id);
    }

    public Category create(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        return categoryRepository.save(category);
    }

    public Category update(Long id, CategoryDto dto) throws NotFoundException {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return categoryRepository.save(category);
    }

    public void delete(Long id) throws NotFoundException {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        categoryRepository.delete(category);
    }
}
