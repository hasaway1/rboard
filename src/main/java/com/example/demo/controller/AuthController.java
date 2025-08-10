package com.example.demo.controller;

import java.util.*;

import io.swagger.v3.oas.annotations.Operation;
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
	@Operation(summary="로그인 여부 확인", description="로그인했으면 아이디와 권한을 리턴")
	@GetMapping(path="/api/auth/check")
	public ResponseEntity<Map<String, String>> checkLogin(Authentication authentication) {
		if(authentication!=null) {
			String username = authentication.getName();
			String role = authentication.getAuthorities().stream().findFirst().get().getAuthority();
			return ResponseEntity.ok(Map.of("username", username, "role", role));
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}
}
