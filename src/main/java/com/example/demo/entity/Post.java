package com.example.demo.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Post {
  private int pno;
  private String title;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String content;
  private String writer;
  @Builder.Default
  @JsonFormat(pattern="yyyy년 MM월 dd일 hh:mm:ss")
  private LocalDateTime writeTime = LocalDateTime.now();
  @Builder.Default
  private int readCnt = 0;
  @Builder.Default
  private int goodCnt = 0;
  @Builder.Default
  private int badCnt = 0;
}
