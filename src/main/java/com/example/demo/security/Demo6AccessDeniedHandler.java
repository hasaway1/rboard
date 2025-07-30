package com.example.demo.security;

import com.example.demo.util.*;
import com.fasterxml.jackson.databind.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.access.*;
import org.springframework.security.web.access.*;
import org.springframework.stereotype.*;

import java.io.*;

// 403(권한 없음)을 처리하는 자바 코드
@Component
public class Demo6AccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    ResponseUtil.sendJsonResponse(response, 403, "작업 권한이 없습니다");
  }
}
