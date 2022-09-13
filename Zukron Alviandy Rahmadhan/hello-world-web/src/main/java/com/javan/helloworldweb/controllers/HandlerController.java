package com.javan.helloworldweb.controllers;

import com.javan.helloworldweb.exceptions.GlobalException;
import com.javan.helloworldweb.exceptions.NotFoundException;
import com.javan.helloworldweb.models.Response;
import com.javan.helloworldweb.utils.StringExtensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ExtensionMethod({StringExtensions.class})
public class HandlerController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error -> {
            String field = ((FieldError) error).getField().camelToSnake();
            String message = error.getDefaultMessage();

            map.put(field, message);
        }));

        return new Response(HttpStatus.BAD_REQUEST, "Validation", map);
    }

    @ExceptionHandler({NotFoundException.class, FileNotFoundException.class})
    @ResponseBody
    public Response handleNotFoundExceptions(Exception e) {
        return new Response(HttpStatus.NOT_FOUND, "Not Found", e.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public Response handleGlobalExceptions(Exception e) {
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "Error", e.getMessage());
    }
}
