package com.zhongsijia.exception;

import com.zhongsijia.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public Result ex(Exception ex) {
    ex.printStackTrace();
    return Result.error("Sorry, there is something wrong. Please contact the administrator.");
  }

}
