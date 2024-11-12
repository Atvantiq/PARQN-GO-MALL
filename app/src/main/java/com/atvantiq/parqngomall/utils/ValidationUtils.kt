package com.atvantiq.parqngomall.utils

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

object ValidationUtils {
	
	var specailCharPatten: Pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
	var UpperCasePatten: Pattern = Pattern.compile("[A-Z ]")
	var lowerCasePatten: Pattern = Pattern.compile("[a-z ]")
	var digitCasePatten: Pattern = Pattern.compile("[0-9 ]")
	
	
	fun isValidPassword(password: String): Int {
		var passwordScore = 0
		
		if (password.length in 8..15) {
			passwordScore++
		}
		
		if (UpperCasePatten.matcher(password).find()) {
			passwordScore++
		}
		if (digitCasePatten.matcher(password).find()) {
			passwordScore++
		}
		
		if (lowerCasePatten.matcher(password).find()) {
			passwordScore++
		}
		
		if (specailCharPatten.matcher(password).find()) {
			passwordScore++
		}
		
		return passwordScore
	}
	
	fun isValidPasswordBool(password: String): Boolean {
		
		if (password.length in 15..8) {
			return false
		}
		
		if (!UpperCasePatten.matcher(password).find()) {
			return false
			
		}
		if (!digitCasePatten.matcher(password).find()) {
			return false
		}
		
		if (!lowerCasePatten.matcher(password).find()) {
			return false
		}
		
		if (!specailCharPatten.matcher(password).find()) {
			return false
		}
		
		return true
	}
	
	fun isValidMobile(phone: String): Boolean {
		var check = false
		check = if (!Pattern.matches("[a-zA-Z]+", phone)) {
			if (phone.length < 9 || phone.length > 13) { // if(phone.length() != 10) {
				false
			} else {
				Patterns.PHONE.matcher(phone).matches()
			}
		} else {
			false
		}
		return check
	}
	
	
	fun isValidEmail(email: String): Boolean {
		return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
	}
	
}