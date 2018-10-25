package fr.dawan.autoquiz3000.helper;

import java.util.Arrays;
import java.util.List;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class Token {
	public static String getToken() {
		List<CharacterRule> rules = Arrays.asList(
				new CharacterRule(EnglishCharacterData.UpperCase, 1),
				new CharacterRule(EnglishCharacterData.LowerCase, 1), 
				new CharacterRule(EnglishCharacterData.Digit, 1));
//				new CharacterRule(EnglishCharacterData.Special, 1));
		PasswordGenerator generator = new PasswordGenerator();
		String token = generator.generatePassword(64, rules);

		return token;
	}
}
