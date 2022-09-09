package com.javan.helloworldweb.repositories;

import com.javan.helloworldweb.models.EmailDetails;

public interface EmailRepository {
    void sendEmail(EmailDetails details);
}
