package com.javan.helloworldweb.repositories;

import com.javan.helloworldweb.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);
}
