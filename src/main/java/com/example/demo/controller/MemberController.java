package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import io.swagger.v3.oas.annotations.*;
import jakarta.servlet.http.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.security.*;
import java.util.*;

@Controller
public class MemberController {
  @Autowired
  private MemberService service;

  @PreAuthorize("isAnonymous()")
  @Operation(summary= "아이디 확인", description="아이디가 사용가능한 지 확인")
  @GetMapping("/api/members/check-username")
  public ResponseEntity<String> checkUsername(@ModelAttribute @Valid MemberDto.UsernameCheck dto) {
    boolean result = service.checkUsername(dto);
    if(result)
      return ResponseEntity.ok("사용가능합니다");
    return ResponseEntity.status(HttpStatus.CONFLICT).body("사용중인 아이디입니다");
  }

  @PreAuthorize("isAnonymous()")
  @Operation(summary="회원가입", description="회원가입 및 프로필 사진 업로드")
  @PostMapping(value="/api/members/new", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Member> signup(@ModelAttribute @Valid MemberDto.Create dto, BindingResult br) {
    Member member = service.signup(dto);
    return ResponseEntity.status(200).body(member);
  }

  @PreAuthorize("isAnonymous()")
  @PutMapping("/api/members/verify")
  @Operation(summary="이메일 코드 확인", description="회원가입 후 코드 확인")
  public ResponseEntity<String> verifyEmail(@RequestParam String code) {
    boolean result = service.verify(code);
    if(result)
      return ResponseEntity.ok("임시비밀번호를 가입 이메일로 보냈습니다");
    return ResponseEntity.status(HttpStatus.CONFLICT).body("사용자를 찾을 수 없습니다");
  }

  @PreAuthorize("isAnonymous()")
  @Operation(summary="아이디 찾기", description="가입한 이메일로 아이디를 찾는다")
  @GetMapping("/api/members/username")
  public ResponseEntity<String> searchUsername(@RequestParam @Email String email) {
    Optional<String> result = service.searchUseraname(email);
    if(result.isPresent())
      return ResponseEntity.ok(result.get());
    return ResponseEntity.status(HttpStatus.CONFLICT).body("사용자를 찾을 수 없습니다");
  }

  @PreAuthorize("isAnonymous()")
  @Operation(summary="임시비밀번호 발급", description="아이디로 임시비밀번호를 발급")
  @PutMapping("/api/members/password")
  public ResponseEntity<String> getTemporaryPassword(@ModelAttribute @Valid MemberDto.ResetPassword dto, BindingResult br) {
    boolean result = service.getTemporaryPassword(dto);
    if(result)
      return ResponseEntity.ok("임시비밀번호를 가입 이메일로 보냈습니다");
    return ResponseEntity.status(HttpStatus.CONFLICT).body("사용자를 찾을 수 없습니다");
  }

  @PreAuthorize("isAuthenticated()")
  @Operation(summary="비밀번호 확인", description="현재 접속 중인 사용자의 비밀번호를 재확인")
  @GetMapping("/api/members/check-password")
  public ResponseEntity<String> checkPassword(@RequestParam String password, Principal principal, HttpSession session) {
    boolean checkSuccess = service.checkPassword(password, principal.getName());
    if(checkSuccess) {
      session.setAttribute("checkPassword", true);
      return ResponseEntity.ok("비밀번호 확인 성공");
    }
    return ResponseEntity.status(HttpStatus.CONFLICT).body("비밀번호 확인 실패");
  }

  @PreAuthorize("isAuthenticated()")
  @Operation(summary = "내 정보 보기", description = "내 정보 보기")
  @GetMapping("/api/members/member")
  public ResponseEntity<MemberDto.Read> read(Principal principal) {
    MemberDto.Read dto = service.read(principal.getName());
    return ResponseEntity.ok(dto);
  }

  @PreAuthorize("isAuthenticated()")
  @Operation(summary = "프사 변경", description = "프사를 변경")
  @PutMapping("/api/members/profile")
  public ResponseEntity<MemberDto.Read> changeProfile(MultipartFile profile, Principal principal) {
    MemberDto.Read member = service.changeProfile(profile, principal.getName());
    return ResponseEntity.ok(member);
  }

  @PreAuthorize("isAuthenticated()")
  @Operation(summary = "비밀번호 변경", description = "기존 비밀번호, 새 비밀번호로 비밀번호 변경")
  @PatchMapping("/api/members/password")
  public ResponseEntity<String> changePassword(@ModelAttribute @Valid MemberDto.PasswordChange dto, BindingResult br, Principal principal) {
    boolean result = service.changePassword(dto, principal.getName());
    if(result)
      return ResponseEntity.ok("비밀번호 변경");
    return ResponseEntity.status(409).body("비밀번호 변경 실패");
  }

  @PreAuthorize("isAuthenticated()")
  @Operation(summary = "회원 탈퇴", description = "로그아웃시킨 후 회원 탈퇴")
  @DeleteMapping("/api/members/member")
  public ResponseEntity<String> resign(Principal principal, HttpSession session) {
    service.resign(principal.getName());
    session.invalidate();
    return ResponseEntity.ok("회원 탈퇴");
  }
}
