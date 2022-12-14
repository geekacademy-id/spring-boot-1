package com.javan.helloworldweb.services;

import com.javan.helloworldweb.exceptions.NotFoundException;
import com.javan.helloworldweb.models.Comment;
import com.javan.helloworldweb.models.EmailDetails;
import com.javan.helloworldweb.models.News;
import com.javan.helloworldweb.models.dto.CommentDto;
import com.javan.helloworldweb.repositories.CommentRepository;
import com.javan.helloworldweb.repositories.NewsRepository;
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
        News news = newsRepository
                .findById(dto.getNewsId())
                .orElseThrow(() -> new NotFoundException("News not found"));

        Comment comment = new Comment();
        comment.setCommentatorName(dto.getCommentatorName());
        comment.setContent(dto.getContent());
        comment.setNews(news);
        comment.setIsBanned(dto.getIsBanned());

        // send email
        EmailDetails details = new EmailDetails();

        String content = String.format("Berita kamu yang berjudul \"%s\" dikomen oleh %s", news.getTitle(), dto.getCommentatorName());
        details.setRecipient(news.getAuthor().getEmail());
        details.setSubject("Ada yang komen nih");
        details.setContent(content);

        emailService.sendEmail(details);
        return commentRepository.save(comment);
    }

    public Comment update(Long id, CommentDto dto) throws NotFoundException {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found"));

        comment.setCommentatorName(dto.getCommentatorName());
        comment.setContent(dto.getContent());
        comment.setIsBanned(dto.getIsBanned());

        return commentRepository.save(comment);
    }

    public void delete(Long id) throws NotFoundException {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found"));

        commentRepository.delete(comment);
    }
}
