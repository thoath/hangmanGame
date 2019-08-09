package com.hangman.dto;

import java.util.ArrayDeque;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Hangman immutable dto object
 * xlm based
 * @author lucas.luz
 *
 */
@XmlRootElement(name = "hangman")
@XmlAccessorType(XmlAccessType.FIELD)
public final class HangmanDto {
	
	@XmlElementWrapper(name = "word_list")
	
	@XmlElement(name = "word")
	private final ArrayDeque<String> wordList = null;
	
	private HangmanDto() {}
	
	public ArrayDeque<String> getWordList() {
		return wordList.clone();
	}
	
	@Override
	public String toString() {
		return "Hangman {" + 
	           "wordList = " + wordList;
	}
	
}
