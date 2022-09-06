package com.mob.portal.Helper;

public interface ResponseFormatter {
    public ResponseFormatter generate(int status, String message, Object data);
    public ResponseFormatter generate(int status, String message);
}
