package com.example.demo.advice;

import com.example.demo.exception.*;
import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ProcessingFaultAdvice {
  // 500 : 처리 중 오류. 정말 다양한 이유로 발생

  // 검증 실패에 대한 예외처리
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<String> constraintViolationException() {
    return ResponseEntity.status(HttpStatus.CONFLICT).body("잘못된 입력 형식");
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
