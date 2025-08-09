package com.example.demo.util.validation;

import jakarta.validation.*;

public class PasswordValidator implements ConstraintValidator<Password, String> {
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		if(password==null || password.isEmpty())
			return true;
		return password.matches("^[0-9a-zA-Z]{6,10}$");
	}
}
