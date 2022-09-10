package com.ichmal.trainingspringboot.crud;

public interface ResponseFormatter {
    public ResponseFormatter generate(int status, String message, Object object);
    public ResponseFormatter generate(int status, String message);
}
