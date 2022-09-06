package com.mob.portal.Helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseFormatter {
    private int status;
    private String message;
    private Object data;
}
