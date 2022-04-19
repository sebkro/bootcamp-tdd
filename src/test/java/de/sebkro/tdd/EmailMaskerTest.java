package de.sebkro.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * - Schreibe einen Service, um E-Mail-Adressen zu maskieren
 * - Es werden nur die Zeichen vor dem @ (local-part) maskiert
 * - Wenn der localpart aus <= 3 Zeichen besteht wird der localpart durch *** ersetzt
 * - Wenn localpart > 3 Zeichen, werden die letzten Zeichen des localparts gesternt
 * - dabei werden mindestens 3 Zeichen gesternt
 * - Es bleiben maximal 3 Zeichen sichtbar
 * <p>
 * Beispiele:
 * <p>
 * "a@cgi.com" --> "***@cgi.com"
 * "ab@cgi.com" --> "***@cgi.com"
 * "abc@cgi.com" --> "***@cgi.com"
 * "pitt@cgi.com" --> "p***@cgi.com"
 * "pittp@cgi.com" --> "pi***@cgi.com"
 * "pittpi@cgi.com" --> "pit***@cgi.com"
 * "pittpit@cgi.com" --> "pit****@cgi.com"
 * "pittpitt@cgi.com" --> "pit*****@cgi.com"
 * "pittpitt123@cgi.com" --> "pit********@cgi.com"
 */
public class EmailMaskerTest {

    private final EmailMasker masker = new EmailMasker();

    @Test
    public void itShouldMaskEmailWithOneLetterInLocalPart() {
        //when
        String result = masker.maskEmail("a@cgi.com");

        //then
        assertEquals("***@cgi.com", result);
    }

    @Test
    public void itShouldMaskEmailWithOneLetterInLocalPartForOtherDomain() {
        //when
        String result = masker.maskEmail("a@test.com");

        //then
        assertEquals("***@test.com", result);
    }

    @Test
    public void itShouldMaskEmailWithTwoLettersInLocalPart() {
        //when
        String result = masker.maskEmail("ab@cgi.com");

        //then
        assertEquals("***@cgi.com", result);
    }

    @Test
    public void itShouldMaskEmailWithThreeLettersInLocalPart() {
        //when
        String result = masker.maskEmail("abc@cgi.com");

        //then
        assertEquals("***@cgi.com", result);
    }

    @Test
    public void itShouldMaskEmailWithFourLettersInLocalPart() {
        //when
        String result = masker.maskEmail("pitt@cgi.com");

        //then
        assertEquals("p***@cgi.com", result);
    }

}