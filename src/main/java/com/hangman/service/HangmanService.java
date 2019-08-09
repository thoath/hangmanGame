package com.hangman.service;

import com.hangman.model.CharacterCheck;
import com.hangman.model.StatusModel;

/**
 * Hangman basic game service
 * @author lucas.luz
 *
 */
public interface HangmanService {
	String XML_PATH = "src/main/resources/Hangman.xml";
	
	CharacterCheck checkCharacter(Character character);
	StatusModel newGame();
	StatusModel getNextWord();
}
