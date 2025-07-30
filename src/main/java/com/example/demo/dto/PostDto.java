package com.example.demo.dto;

import com.example.demo.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.*;
import java.util.*;

// PostDto는 Dto들을 담는 클래스다 -> Dto 클래스 개수 줄여 PostDto.Pages, PostDto.Create....
public class PostDto {
  // 페이징 출력 DTO
  @Data
  @AllArgsConstructor
  public static class Pages {
    private int prev;
    private int start;
    private int end;
    private int next;
    private int pageno;
    private List<Post> posts;
  }

  // 글을 작성하는 DTO
  @Data
  public static class Write {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    public Post toEntity(String loginId) {
      return Post.builder().title(title).content(content).writer(loginId).build();
    }
  }

  // 글 변경 DTO
  @Data
  public static class Update {
    @NotNull
    private Integer pno;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Read {
    private Integer pno;
    private String title;
    private String content;
    private String writer;
    @JsonFormat(pattern="yyyy년 MM월 dd일 hh:mm:ss")
    private LocalDateTime writeTime;
    private Integer readCnt;
    private Integer goodCnt;
    private Integer badCnt;
    private List<Comment> comments;
  }
}
