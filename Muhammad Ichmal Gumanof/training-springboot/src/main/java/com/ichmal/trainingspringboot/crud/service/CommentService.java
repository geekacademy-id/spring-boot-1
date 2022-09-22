package com.ichmal.trainingspringboot.crud.service;

import com.ichmal.trainingspringboot.crud.NotFoundException;
import com.ichmal.trainingspringboot.crud.email.EmailDetail;
import com.ichmal.trainingspringboot.crud.email.EmailService;
import com.ichmal.trainingspringboot.crud.models.Comment;
import com.ichmal.trainingspringboot.crud.models.News;
import com.ichmal.trainingspringboot.crud.models.dto.CommentDto;
import com.ichmal.trainingspringboot.crud.repository.CommentRepository;
import com.ichmal.trainingspringboot.crud.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private EmailService emailService;

    public List<Comment> list() {
        return commentRepository.findAll();
    }

    public Comment create(CommentDto dto) throws NotFoundException {
        News news = newsRepository.findById(dto.getNewsId()).orElseThrow(() -> new NotFoundException("News not found"));

        Comment comment = new Comment();
        comment.setComentatorName(dto.getComentatorName());
        comment.setContent(dto.getContent());
        comment.setNews(news);
        comment.setIsBanned(dto.getIsBanned());

        // Send Email
        EmailDetail detail = new EmailDetail();

        String content = String.format("Berita kamu yang berjudul \"%s\" dikomen oleh %s", news.getTitle(), dto.getComentatorName());
        detail.setRecipient(news.getAuthor().getEmail());
        detail.setSubject("Berita kamu ada yang komen");
        detail.setContent(content);

        emailService.sendEmail(detail);
        return commentRepository.save(comment);
    }

    public Comment update(Long id, CommentDto dto) throws NotFoundException {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment not found"));

        comment.setComentatorName(dto.getComentatorName());
        comment.setContent(dto.getContent());
        comment.setIsBanned(dto.getIsBanned());

        return commentRepository.save(comment);
    }

    public void delete(Long id) throws NotFoundException {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }
}
