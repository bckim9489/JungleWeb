package com.jungle.jungleweb.domain;

import com.jungle.jungleweb.util.ResponseStatus;
import lombok.*;



@Getter
@AllArgsConstructor
public class ResponseDTO<T> {
    private final ResponseStatus status;
    private final String message;
    private final T data;
}
