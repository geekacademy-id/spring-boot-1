package com.javan.helloworldweb.repositories;

import com.javan.helloworldweb.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    // search by title or content
    List<News> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);

    // search by title or content or author fullname
    List<News> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrAuthor_FullnameContainingIgnoreCase(String title, String content, String author);

    @Query("select n from News n join Author a on n.author.id = a.id where n.title like %?1% or n.title like %?1% or a.fullname like %?1%")
    List<News> findAllByKeyword(String keyword);
}
