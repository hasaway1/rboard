package com.example.demo.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
  private int cno;
  private String content;
  private String writer;
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime writeTime;
  private int pno;
}
