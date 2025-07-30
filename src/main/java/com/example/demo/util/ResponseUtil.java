package com.example.demo.util;

import com.fasterxml.jackson.databind.*;
import jakarta.servlet.http.*;

import java.io.*;

// REST 방식에서는 스프링 시큐리티로 로그인등의 작업을 수행하면 상태코드 + 메시지를 출력해야 한다
// 스프링 시큐리티의 기본은 MVC 방식(즉 html을 출력) -> 개발자가 변경해야 한다
// 그런데 스프링 시큐리티는 ResponseEntity를 사용할 수 없다

public class ResponseUtil {
  // json 응답을 만들어낼 Jackson 객체
  // 스프링에서는 Jackson 의존성 -> 빈 등록 -> ResponseEntity 출력하면 알아서 json 출력 (자동으로 진행)
  private static final ObjectMapper mapper = new ObjectMapper();

  public static void sendJsonResponse(HttpServletResponse res, int statusCode, Object body) throws IOException {
    res.setStatus(statusCode);
    // 출력 데이터 형식을 json으로 + 한글 설정
    res.setContentType("application/json; charset=UTF-8");
    res.getWriter().write(mapper.writeValueAsString(body));
  }
}
