package com.example.demo.util.validation;

import jakarta.validation.*;

public class UsernameValidator implements ConstraintValidator<Username, String> {
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		// null일때 true를 리턴하면 선택, false를 리턴하면 필수 입력
		if(username==null)
			return false;
		return username.matches("^[0-9A-Z]{8,10}$");
	}
}
