package com.example.demo.security;

import com.example.demo.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.*;
import org.springframework.stereotype.*;

import java.io.*;

// 401 처리
// MVC 방식 SS 설정에서는 401이 발생하면 로그인 페이지로 이동 처리
// REST 방식은 로그인 페이지를 백이 아니라 프론트가 띄운다
@Component
public class Demo6AuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    ResponseUtil.sendJsonResponse(response, 401, "로그인이 필요합니다");
  }
}
