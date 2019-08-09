package com.hangman.model;

import java.util.List;
import java.util.Map;

/**
 * hangman response object 
 * @author lucas.luz
 *
 */
public class CharacterCheck {

	private List<Character> characterUsed;
	private Map<Integer,Character> charactersFound;
	private int attempts;
	private int maxAttempts;
	private int currentWordLength;
	private boolean isGameOver;
	private boolean isLastWord;
	private boolean isWordCompleted;
	
	
	public CharacterCheck(List<Character> characterUsed, Map<Integer, Character> charactersFound, int attempts,
			int maxAttempts, int currentWordLength, boolean isGameOver, boolean isLastWord, boolean isWordCompleted) {
		super();
		this.characterUsed = characterUsed;
		this.charactersFound = charactersFound;
		this.attempts = attempts;
		this.maxAttempts = maxAttempts;
		this.currentWordLength = currentWordLength;
		this.isGameOver = isGameOver;
		this.isLastWord = isLastWord;
		this.isWordCompleted = isWordCompleted;
	}
	
	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	public boolean isGameOver() {
		return isGameOver;
	}
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	public boolean isLastWord() {
		return isLastWord;
	}
	public void setLastWord(boolean isLastWord) {
		this.isLastWord = isLastWord;
	}
	public boolean isWordCompleted() {
		return isWordCompleted;
	}
	public void setWordCompleted(boolean isWordCompleted) {
		this.isWordCompleted = isWordCompleted;
	}
	public List<Character> getCharacterUsed() {
		return characterUsed;
	}
	public void setCharacterUsed(List<Character> characterUsed) {
		this.characterUsed = characterUsed;
	}
	public int getCurrentWordLength() {
		return currentWordLength;
	}
	public void setCurrentWordLength(int currentWordLength) {
		this.currentWordLength = currentWordLength;
	}
	public Map<Integer, Character> getCharactersFound() {
		return charactersFound;
	}
	public void setCharactersFound(Map<Integer, Character> charactersFound) {
		this.charactersFound = charactersFound;
	}
	public int getMaxAttempts() {
		return maxAttempts;
	}
	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}
}
