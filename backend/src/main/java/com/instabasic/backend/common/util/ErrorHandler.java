package com.instabasic.backend.common.util;

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
