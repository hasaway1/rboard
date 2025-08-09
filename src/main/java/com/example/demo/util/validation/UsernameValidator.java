package com.example.demo.util.validation;

import jakarta.validation.*;

public class UsernameValidator implements ConstraintValidator<Username, String> {
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(username==null || username.isEmpty())
			return true;
		return username.matches("^[0-9A-Z]{6,10}$");
	}
}
