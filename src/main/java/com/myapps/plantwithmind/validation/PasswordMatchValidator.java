package com.myapps.plantwithmind.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.myapps.plantwithmind.model.SiteUser;



public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, SiteUser> {

	public void initialize(PasswordMatch p) {
		
	}

	public boolean isValid(SiteUser user, ConstraintValidatorContext c) {
		
		String plainPassword = user.getPlainPassword();
		String repeatPassword = user.getRepeatPassword();
		
		if(plainPassword == null || plainPassword.length() == 0) {
			return true;
		}
		
		if(plainPassword == null || !plainPassword.equals(repeatPassword)) {
			return false;
		}
			
		return true;
	}

}
