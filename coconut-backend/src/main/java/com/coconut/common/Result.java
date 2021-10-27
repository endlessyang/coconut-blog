package com.coconut.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class Result implements Serializable {

    private int code;
    private String message;
    private Object data;

    public static Result success(Object data) {
        return new Result(200, "Success", data);
    }

    public static Result fail(String message) {
        return new Result(400, message, null);
    }

}
