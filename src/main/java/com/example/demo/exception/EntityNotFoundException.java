package com.example.demo.exception;

import lombok.*;

// 자바의 예외
// Exception의 자식들 : try ~ catch 예외처리 강제(체크하는 예외)
//   ↖ RuntimeException의 자식을 : 예외처리 선택(체크하지 않는 예외)
// 스프링 예외처리는 보통 체크하지 않는 예외를 사용한다 -> RuntimeException은 직접 사용하기는 너무 광범위
@AllArgsConstructor
@Getter
public class EntityNotFoundException extends RuntimeException {
  private String message;
}
