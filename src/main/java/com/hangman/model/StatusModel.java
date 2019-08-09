package com.hangman.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Transient singleton object to operate player status
 * @author lucas.luz
 *
 */
public class StatusModel {

	private int attempts;
	private final int maxAttempts = 6;
	private String currentWord;
	private List<Character> charUsed = new ArrayList<>();
	private Map<Integer,Character> charactersFound = new HashMap<>();
	
	private static transient volatile StatusModel instance;
	
	private StatusModel() {}
	
	public static StatusModel getInstance() {
		
		if(instance == null) {
			synchronized (StatusModel.class) {
				if(instance == null) {
					instance = new StatusModel();
				}
			}
		}
		
		return instance;
	}
	
	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public String getCurrentWord() {
		return currentWord;
	}

	public void setCurrentWord(String currentWord) {
		this.currentWord = currentWord;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}

	public void sumAttempts() {
		this.attempts++;
	}

	public List<Character> getCharUsed() {
		return charUsed;
	}

	public void setCharUsed(List<Character> charUsed) {
		this.charUsed = charUsed;
	}
	
	public void clearCharUsed() {
		this.charUsed.clear();
	}
	
	public void clearCharFound() {
		this.charactersFound.clear();
	}

	public Map<Integer, Character> getCharactersFound() {
		return charactersFound;
	}

	public void setCharactersFound(Map<Integer, Character> charactersFound) {
		this.charactersFound = charactersFound;
	}
}
