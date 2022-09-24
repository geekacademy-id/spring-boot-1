package com.ichmal.trainingspringboot.crud.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailRepository {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username")
    private String sender;

    @Override
    public void sendEmail(EmailDetail detail){
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(detail.getRecipient());
            mailMessage.setText(detail.getContent());
            mailMessage.setSubject(detail.getSubject());

            javaMailSender.send(mailMessage);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

}
