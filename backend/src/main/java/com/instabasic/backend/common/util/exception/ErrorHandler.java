package com.instabasic.backend.common.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorHandler extends RuntimeException {

    private int status;
    private String message;

}
