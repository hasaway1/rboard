package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import io.swagger.v3.oas.annotations.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.annotation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.security.*;

@RestController
public class PostController {
  @Autowired
  private PostService service;

  @Operation(summary="페이징", description="기본 페이지번호 1, 페이지크기 10으로 페이징")
  @GetMapping("/api/posts")
  public ResponseEntity<PostDto.Pages> findAll(@RequestParam(defaultValue="1") int pageno, @RequestParam(defaultValue="10") int pagesize) {
    return ResponseEntity.ok(service.findAll(pageno, pagesize));
  }

  @Validated
  @Operation(summary="글읽기", description="글읽기")
  @GetMapping("/api/posts/post")
  public ResponseEntity<PostDto.Read> findByPno(@RequestParam(required=false) @NotNull(message="글번호는 필수입력입니다") Integer pno, Principal principal) {
    String loginId = principal==null? null : principal.getName();
    return ResponseEntity.ok(service.findByPno(pno, loginId));
  }

  @Secured("ROLE_USER")
  @Operation(summary="글쓰기", description="제목과 내용으로 글 작성")
  @PostMapping("/api/posts/new")
  public ResponseEntity<Post> write(@ModelAttribute @Valid PostDto.Write dto, Principal principal) {
    Post post = service.write(dto, principal.getName());
    return ResponseEntity.ok(post);
  }

  @Secured("ROLE_USER")
  @Operation(summary="글변경", description="글번호로 제목과 내용 변경")
  @PutMapping("/api/posts/post")
  public ResponseEntity<String> update(@ModelAttribute @Valid PostDto.Update dto, Principal principal) {
    service.update(dto, principal.getName());
    return ResponseEntity.ok("글을 변경했습니다");
  }

  @Validated
  @Secured("ROLE_USER")
  @Operation(summary="삭제", description="글번호로 삭제")
  // consumes를 지정하면 RequestBody로 해석한다(Swagger 마음대로...)
  @DeleteMapping("/api/posts/post")
  public ResponseEntity<String> delete(@RequestParam(required=false) @NotNull(message="글번호는 필수입력입니다") Integer pno, Principal principal) {
    service.delete(pno, principal.getName());
    return ResponseEntity.ok("글을 삭제했습니다");
  }

  @Validated
  @Secured("ROLE_USER")
  @Operation(summary="글추천", description="이미 추천한 글 재추천 불가")
  @PutMapping("/api/posts/good")
  public ResponseEntity<Integer> good(@RequestParam(required=false) @NotNull(message="글번호는 필수입력입니다") Integer pno, Principal principal) {
    int newGoodCnt = service.good(pno, principal.getName());
    return ResponseEntity.ok(newGoodCnt);
  }
}
