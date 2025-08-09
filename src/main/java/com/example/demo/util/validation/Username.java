package com.example.demo.util.validation;

import java.lang.annotation.*;

import jakarta.validation.*;

// 어노테이션의 위치 : 클래스 위, 필드 위, 메소드 위
@Target({ElementType.FIELD, ElementType.PARAMETER})
// 언제 동작할거니? 실행중에
@Retention(RetentionPolicy.RUNTIME)
// 어노테이션을 처리하는 클래스를 지정해야 한다
@Constraint(validatedBy=UsernameValidator.class)
public @interface Username {
	String message() default "아이디는 대문자와 숫자 8~10자입니다";
	
	// ?는 임의의 클래스라는 뜻. Object와의 차이점은 java generic 강좌들을 참고
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {}; 
}
