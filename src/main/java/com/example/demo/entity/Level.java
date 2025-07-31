package com.example.demo.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@AllArgsConstructor
public enum Level {
  NORMAL("고마운 분"), SILVER("귀한 분"), GOLD("천생연분");

  // enum에 한글화 파라미터를 추가하면, 추가한 파라미터를 필드로
  private final String name;

  @JsonValue
  public String toJson() {
    return this.name() + " (" + name + ")";
  }
}
