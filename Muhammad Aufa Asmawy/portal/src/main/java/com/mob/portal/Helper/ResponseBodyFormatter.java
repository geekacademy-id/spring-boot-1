package com.mob.portal.Helper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class ResponseBodyFormatter {
    private boolean status;
    private List<String> message = new ArrayList<>();
    private Object data;

    public ResponseBodyFormatter success(String message, Object data){
         this.status = true;
         this.message.clear();
         this.message.add(message);
         this.data = data;
         return this;
    }

    public ResponseBodyFormatter error(String message){
        this.status = false;
        this.message.clear();
        this.message.add(message);
        this.data = null;
        return this;
    }

    public ResponseBodyFormatter error(Errors errors){
         this.status = false;
         this.message.clear();
         for (ObjectError error : errors.getAllErrors()){
             message.add(error.getDefaultMessage());
         }
         this.data = null;
         return this;
    }
}
