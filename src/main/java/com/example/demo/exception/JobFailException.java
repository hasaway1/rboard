package com.example.demo.exception;

import lombok.*;

@AllArgsConstructor
@Getter
public class JobFailException extends RuntimeException {
  private String message;
}
