package com.example.springboot.repository;

import com.example.springboot.models.TagNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagNewsRepository extends JpaRepository<TagNews, Long> {
}
