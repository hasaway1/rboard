package com.example.demo;

import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;
import io.swagger.v3.oas.models.*;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI openAPI() {
    // http://localhost:8080/swagger-ui/index.html
    Info info = new Info().title("최종예제 문서화").description("게시판 API 레퍼런스 문서화").version(("1.0"));
    return new OpenAPI().components(new Components()).info(info);
  }
}
