package com.jungle.jungleweb.util;

import com.jungle.jungleweb.domain.ResponseDTO;

public class ResponseUtil {

    public static <T> ResponseDTO<T> SUCCESS (String message, T data) {
        return new ResponseDTO<T>(ResponseStatus.SUCCESS, message, data);
    }

    public static <T>ResponseDTO <T> FAILURE (String message, T data) {
        return new ResponseDTO<T>(ResponseStatus.FAILURE, message, data);
    }

    public static <T>ResponseDTO <T> ERROR (String message, T data) {
        return new ResponseDTO<T>(ResponseStatus.ERROR, message, data);
    }
}
