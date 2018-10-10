package de.sebkro.tdd;

public class EmailMasker {

	public String maskEmail(String string) {
		String[] splitted = string.split("@");
		return "***@" + splitted[1];
	}

}
