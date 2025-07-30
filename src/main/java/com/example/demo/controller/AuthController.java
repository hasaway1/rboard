package com.example.demo.controller;

import java.util.*;

import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;

@RequiredArgsConstructor
@Controller
public class AuthController {
	// 프론트엔드에서 현재 로그인 상태를 물어오면 응답 
	// 로그인한 경우 : 200 + 로그인 아이디
	// 비로그인 : 409 + null
	@GetMapping(path="/api/auth/check")
	public ResponseEntity<Map<String, String>> checkLogin(Authentication authentication) {
		if(authentication!=null) {
			String username = authentication.getName();
			String role = authentication.getAuthorities().stream().findFirst().get().getAuthority();
			return ResponseEntity.ok(Map.of("username", username, "role", role));
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping(path="/api/auth/check-password")
	public ResponseEntity<Void> isCheckPassword(HttpSession session) {
		if(session.getAttribute("checkPassword") != null)
			return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}
}
