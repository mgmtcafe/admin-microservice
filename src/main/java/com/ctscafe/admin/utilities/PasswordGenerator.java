package com.ctscafe.admin.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordGenerator {
	
	public static String generatePassword() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*.";
		String pwd = RandomStringUtils.random( 8, characters );
		return pwd;
	}
	
}
