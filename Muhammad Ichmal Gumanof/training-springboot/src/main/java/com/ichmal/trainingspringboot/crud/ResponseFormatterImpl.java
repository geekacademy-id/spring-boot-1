package com.ichmal.trainingspringboot.crud;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ResponseFormatterImpl implements ResponseFormatter{
    private int status;
    private String message;
    private Object object;

    public ResponseFormatter generate(int status, String message, Object object){
        this.status = status;
        this.message = message;
        this.object = object;
        return this;
    }

    public ResponseFormatter generate(int status, String message){
        this.status = status;
        this.message = message;
        return this;
    }
}
