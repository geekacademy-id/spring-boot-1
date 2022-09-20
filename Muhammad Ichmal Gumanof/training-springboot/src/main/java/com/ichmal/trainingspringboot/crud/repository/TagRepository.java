package com.ichmal.trainingspringboot.crud.repository;

import com.ichmal.trainingspringboot.crud.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
