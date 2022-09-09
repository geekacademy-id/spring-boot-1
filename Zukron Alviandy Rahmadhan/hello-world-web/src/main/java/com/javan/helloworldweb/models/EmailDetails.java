package com.javan.helloworldweb.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDetails {
    private String recipient;
    private String subject;
    private String content;
}
