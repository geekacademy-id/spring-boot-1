package com.javan.helloworldweb.services;

import com.javan.helloworldweb.models.EmailDetails;
import com.javan.helloworldweb.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailRepository {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void sendEmail(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getContent());
            mailMessage.setSubject(details.getSubject());

            mailSender.send(mailMessage);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
