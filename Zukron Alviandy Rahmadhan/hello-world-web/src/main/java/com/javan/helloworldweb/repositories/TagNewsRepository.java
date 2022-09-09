package com.javan.helloworldweb.repositories;

import com.javan.helloworldweb.models.TagNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagNewsRepository extends JpaRepository<TagNews, Long> {
}
