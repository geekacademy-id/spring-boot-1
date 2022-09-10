package com.ichmal.trainingspringboot.crud.repository;

import com.ichmal.trainingspringboot.crud.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
