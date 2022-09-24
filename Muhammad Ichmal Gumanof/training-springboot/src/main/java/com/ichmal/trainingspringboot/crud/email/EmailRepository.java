package com.ichmal.trainingspringboot.crud.email;

public interface EmailRepository {
    void sendEmail(EmailDetail detail);
}
