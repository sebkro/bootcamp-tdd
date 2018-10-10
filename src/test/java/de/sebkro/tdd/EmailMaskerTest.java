package de.sebkro.tdd;

import org.junit.Assert;
import org.junit.Test;

/**
 * - Schreibe einen Service, um E-Mail-Adressen zu maskieren
 * - Es werden nur die Zeichen vor dem @ (local-part) maskiert
 * - Wenn der localpart aus <= 3 Zeichen besteht wird der localpart durch *** ersetzt
 * - Wenn localpart > 3 Zeichen, werden die letzten Zeichen des localparts gesternt
 * 		- dabei werden mindestens 3 Zeichen gesternt
 * 		- Es bleiben maximal 3 Zeichen sichtbar
 * 
 * Beispiele:
 * 
 * "aks@cgi.com" --> "***@cgi.com"
 * "ak@cgi.com" --> "***@cgi.com"
 * "a@cgi.com" --> "***@cgi.com"
 * "pitt@cgi.com" --> "p***@cgi.com"
 * "pittp@cgi.com" --> "pi***@cgi.com"
 * "pittpi@cgi.com" --> "pit***@cgi.com"
 * "pittpit@cgi.com" --> "pit****@cgi.com"
 * "pittpitt@cgi.com" --> "pit*****@cgi.com"
 * "pittpitt123@cgi.com" --> "pit********@cgi.com"	 
 *
 */
public class EmailMaskerTest {
	
	private EmailMasker masker = new EmailMasker();
	
	@Test
	public void itShouldMaskEmailWithOneLetterInLocalPart() {
		//when
		String result = masker.maskEmail("a@test.com");
		
		//then
		Assert.assertEquals("***@test.com", result);
	}

	@Test
	public void itShouldMaskEmailWithOneLetterInLocalPartForOtherDomain() {
		//when
		String result = masker.maskEmail("a@foo.com");
		
		//then
		Assert.assertEquals("***@foo.com", result);
	}

	@Test
	public void itShouldMaskEmailWithTwoLettersInLocalPart() {
		//when
		String result = masker.maskEmail("ab@foo.com");
		
		//then
		Assert.assertEquals("***@foo.com", result);
	}

	@Test
	public void itShouldMaskEmailWithThreeLettersInLocalPart() {
		//when
		String result = masker.maskEmail("abc@foo.com");
		
		//then
		Assert.assertEquals("***@foo.com", result);
	}

	@Test
	public void itShouldMaskEmailWithFourLettersInLocalPart() {
		//when
		String result = masker.maskEmail("abcd@foo.com");
		
		//then
		Assert.assertEquals("a***@foo.com", result);
	}

}
