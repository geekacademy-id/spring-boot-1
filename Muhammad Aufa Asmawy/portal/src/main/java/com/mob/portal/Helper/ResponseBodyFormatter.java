package com.mob.portal.Helper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class ResponseBodyFormatterImpl implements ResponseBodyFormatter {
    private boolean status;
    private List<String> message;
    private Object data;

//    public ResponseBodyFormatter success(Object message, Object data){
//         this.status = true;
//         this.message = message;
//         this.data = data;
//         return this;
//    }
//    public ResponseBodyFormatter error(Object message){
//         this.status = false;
//         this.message = message;
//         this.data = null;
//         return this;
//    }
}
