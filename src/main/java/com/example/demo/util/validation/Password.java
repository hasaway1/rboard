package com.example.demo.util.validation;

import java.lang.annotation.*;

import jakarta.validation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=PasswordValidator.class)
public @interface Password {
	String message() default "비밀번호는 영숫자 6~10자입니다";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {}; 
}
