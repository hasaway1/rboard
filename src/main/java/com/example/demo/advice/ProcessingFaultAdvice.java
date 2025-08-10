package com.example.demo.advice;

import com.example.demo.exception.*;
import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class ProcessingFaultAdvice {
  // 500 : 처리 중 오류. 정말 다양한 이유로 발생

  // 바인딩 시점에서 검증
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
    System.out.println("method");
    return ResponseEntity.status(409).body(e.getAllErrors().get(0).getDefaultMessage());
  }

  // 바인딩 이후 예외 처리 + 파라미터 검증에서 경우에 따라 발생할 수 있음
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<String> constraintViolationException(ConstraintViolationException e) {
    return ResponseEntity.status(409).body(e.getMessage());
  }

  // 파라미터 검증 실패에 대한 예외 처리
  @ExceptionHandler(HandlerMethodValidationException.class)
  public ResponseEntity<String> handlerMethodValidationException(HandlerMethodValidationException e) {
    String message = e.getAllErrors().get(0).getDefaultMessage();
    return ResponseEntity.status(409).body(message);
  }


  // 사용자 정의 : 엔티티 클래스(회원,글,댓글)가 없을 때
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> entityNotFoundException(EntityNotFoundException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }

  // 사용자 정의 : 작업이 실패했을 때
  @ExceptionHandler(JobFailException.class)
  public ResponseEntity<String> jobFailException(JobFailException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }
}
