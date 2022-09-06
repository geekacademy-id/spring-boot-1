package com.mob.portal.Helper;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ResponseFormatterImpl implements ResponseFormatter{
    private int status;
    private String message;
    private Object data;

    public ResponseFormatter generate(int status, String message, Object data){
         this.status = status;
         this.message = message;
         this.data = data;
         return this;
    }
    public ResponseFormatter generate(int status, String message){
         this.status = status;
         this.message = message;
         return this;
    }
}
