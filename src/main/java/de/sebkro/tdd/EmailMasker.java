package de.sebkro.tdd;

public class EmailMasker {

	public String maskEmail(String string) {
		String[] split = string.split("@");
		return "***@" + split[1];
	}

}