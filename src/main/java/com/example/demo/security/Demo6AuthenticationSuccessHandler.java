package com.example.demo.security;

import com.example.demo.dao.*;
import com.example.demo.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;

@Component
public class Demo6AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  @Autowired
  private MemberDao memberDao;
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    String username = authentication.getName();
    String role = authentication.getAuthorities().stream().findFirst().get().getAuthority();
    ResponseUtil.sendJsonResponse(response, 200, Map.of("username", username, "role", role));
  }
}








