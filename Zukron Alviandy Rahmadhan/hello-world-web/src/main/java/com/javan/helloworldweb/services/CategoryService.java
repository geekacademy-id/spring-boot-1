package com.javan.helloworldweb.services;

import com.javan.helloworldweb.exceptions.NotFoundException;
import com.javan.helloworldweb.models.Category;
import com.javan.helloworldweb.models.dto.CategoryDto;
import com.javan.helloworldweb.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> list() {
        return categoryRepository.findAll();
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

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
