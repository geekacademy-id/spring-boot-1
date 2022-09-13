package com.ichmal.trainingspringboot.crud.repository;

import com.ichmal.trainingspringboot.crud.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
