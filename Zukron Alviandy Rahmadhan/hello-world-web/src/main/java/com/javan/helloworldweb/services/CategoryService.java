package com.javan.helloworldweb.services;

import com.javan.helloworldweb.models.Category;
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

    public void create(Category category) {
        categoryRepository.save(category);
    }

    public void update(Long id, Category category) {
        categoryRepository.findById(id)
                .ifPresent(item -> {
                    item.setName(category.getName());
                    item.setDescription(category.getDescription());

                    categoryRepository.save(item);
                });
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
