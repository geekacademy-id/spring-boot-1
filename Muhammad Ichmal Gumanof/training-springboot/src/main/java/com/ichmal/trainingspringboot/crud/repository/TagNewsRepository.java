package com.ichmal.trainingspringboot.crud.repository;

import com.ichmal.trainingspringboot.crud.models.TagNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagNewsRepository extends JpaRepository<TagNews, Long> {
}
