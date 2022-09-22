package com.ichmal.trainingspringboot.crud.email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDetail {
    private String recipient;
    private String subject;
    private String content;
}
