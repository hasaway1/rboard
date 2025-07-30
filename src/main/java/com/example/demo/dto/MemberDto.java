package com.example.demo.dto;

import com.example.demo.entity.*;
import com.fasterxml.jackson.annotation.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.*;

import java.time.*;

public class MemberDto {
  @Data
  public static class UsernameCheck {
    //@NotEmpty
    //@Pattern(regexp="^[a-z0-9]{6,10}$")
    private String username;
  }

  @Data
  public static class Create {
    // 아이디는 소문자와 숫자 6~10자 -> 문자열 패턴을 검증할 때 사용하는 기술 : "정규식"
    // [a-z] : 소문자 한개, [0-9] : 숫자 1개
    // [a-z0-9] : 범위들을 나열하면 또는으로 연결 -> 소문자 또는 숫자 1글자
    // [a-z0-9]{6,10} : 소문자 또는 숫자 6~10자(을 포함하는)
    //                  현재 패턴에 "111111111111111"을 주면 통과
    // 아이디는 소문자와 숫자가 6글자이상, 10글자를 초과하면 안된다
    // ^[a-z0-9]{6,10}$ : ^ 시작한다는 뜻, $ 끝난다
    @NotEmpty
    @Pattern(regexp="^[a-z0-9]{6,10}$")
    private String username;
    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9]{6,10}$")
    private String password;
    @Email
    @NotEmpty
    private String email;
    private MultipartFile profile;

    // DTO를 엔티티로 변환하는 메소드
    public Member toEntity(String encodedPassword, String base64Image, String code) {
      return Member.builder().username(username).password(encodedPassword).email(email).profile(base64Image).isLock(true).code(code).build();
    }
  }

  @Data
  public static class ResetPassword {
    @NotEmpty
    @Pattern(regexp="^[a-z0-9]{6,10}$")
    private String username;
  }

  @Data
  @AllArgsConstructor
  public static class Read {
    private String username;
    private String email;
    private String profile;
    @JsonFormat(pattern="yyyy년 MM월 dd일")
    private LocalDate joinday;
    // 가입기간
    private long days;
    private Level level;
  }

  @Data
  public static class PasswordChange {
    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9]{6,10}$")
    private String currentPassword;
    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9]{6,10}$")
    private String newPassword;
  }

}










