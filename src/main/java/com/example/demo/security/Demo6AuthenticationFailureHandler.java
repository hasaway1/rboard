package com.example.demo.security;

import com.example.demo.dao.*;
import com.example.demo.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import java.io.*;

@Component
public class Demo6AuthenticationFailureHandler implements AuthenticationFailureHandler {
  @Autowired
  private MemberDao memberDao;

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    if(exception instanceof LockedException)
      ResponseUtil.sendJsonResponse(response, 403, "비활성화된 계정입니다");
    else
      ResponseUtil.sendJsonResponse(response, 401, "아이디나 비밀번호를 확인하세요");
  }
}
