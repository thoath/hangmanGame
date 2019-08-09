package com.hangman.service;

import java.util.ArrayDeque;

import org.springframework.util.StringUtils;

import com.hangman.dto.HangmanDto;
import com.hangman.exception.HangmanException;
import com.hangman.model.CharacterCheck;
import com.hangman.model.StatusModel;
import com.hangman.util.HangmanUtilSingleton;

/**
 * Hangman basic service implementation
 * @author lucas.luz
 *
 */
public class HangmanServiceImpl implements HangmanService{
	
	private static ArrayDeque<String> mutableList;
	
	/**
	 * 
	 *	Process the  typed character, and update player status
	 */
	@Override
	public CharacterCheck checkCharacter(Character character) {
		
		validateInput(character);

		getStatusModel().getCharUsed().add(character);
		
	    int index = getStatusModel().getCurrentWord().indexOf(character);
	    boolean found = false;
	    
	    while (index >=0){
	    	found = true;
	    	getStatusModel().getCharactersFound().put(index, character);
	    	index = getStatusModel().getCurrentWord().indexOf(character, index+1);
	    }
	
	    if (found) {
	    	
	    	return new CharacterCheck( 
	    			getStatusModel().getCharUsed(),
	    			getStatusModel().getCharactersFound(),
	    			getStatusModel().getAttempts(),
	    			getStatusModel().getMaxAttempts(),
	    			getStatusModel().getCurrentWord().length(),
	    			IsGameOver(),
	    			IsLastWord(),
	    			IsWordCompleted());
	    }
		
	    getStatusModel().sumAttempts();
		
    	return new CharacterCheck( 
    			getStatusModel().getCharUsed(),
    			getStatusModel().getCharactersFound(),
    			getStatusModel().getAttempts(),
    			getStatusModel().getMaxAttempts(),
    			getStatusModel().getCurrentWord().length(),
    			IsGameOver(),
    			IsLastWord(),
    			IsWordCompleted());
	}

	/*
	 * Validate the player input
	 *  - Verify if the game has been started
	 *  - Verify if the character typed is valid
	 *  - Verify if the character typed was already used 
	 * @param character
	 */
	private void validateInput(Character character) {
		
		if (StringUtils.isEmpty(getStatusModel().getCurrentWord())) {
			throw new HangmanException("O jogo ainda não foi iniciado!");
		}
		
		if (character == null || !Character.isLetter(character)) {
			throw new HangmanException("Use Apenas Letras!");
		}
		
		if (getStatusModel().getCharUsed().contains(character)) {
			throw new HangmanException("Letra já foi usada!");
		}
	}
	
	/**
	 * Retrieve the guessing words from xml
	 * @return
	 */
	public HangmanDto getHangmanWords() {
		  
	  	try {
			return HangmanUtilSingleton.getInstance().getObectFromXMl(XML_PATH, HangmanDto.class);
		} catch (Exception e) {
			throw new HangmanException("Erro ao iniciar lista de palavras");
		}
	}
	
	/**
	 * Start a new game, reseting the player status and the guessing word list
	 */
	@Override
	public StatusModel newGame() {

		setMutableList(getHangmanWords().getWordList()).getNextWord();
		
		return getStatusModel();
		
	}
	
	/**
	 * The next word from the guessing word list
	 */
	@Override
	public StatusModel getNextWord() {
		
		if (getMutableList() != null && getMutableList().peek() != null) {
			getStatusModel().setCurrentWord(getMutableList().pop());
			getStatusModel().setAttempts(0);
			getStatusModel().clearCharUsed();
			getStatusModel().clearCharFound();
			
			return getStatusModel();
		}
		
		return null;
	}
	
	/**
	 * Verify if the current word is the last from the list
	 * @return
	 */
	public boolean IsLastWord() {
		return mutableList.size() <= 0;
	}

	/**
	 * Verify if the max attempts was reached 
	 * @return
	 */
	public boolean IsGameOver() {
		return getStatusModel().getAttempts() >= getStatusModel().getMaxAttempts();
	}
	
	/**
	 * Retrieve the current guessing word
	 * @return
	 */
	public String getCurrentWord() {
		return getStatusModel().getCurrentWord();
	}
	
	/**
	 * verify if the word has all the characters
	 * @return
	 */
	public boolean IsWordCompleted() {
		if (getStatusModel().getCharactersFound().size()
    			== getStatusModel().getCurrentWord().length()) {
    		return true;
    	}
		return false;
	}
	
	/**
	 * 
	 * @return the mutable guessing list
	 */
	public ArrayDeque<String> getMutableList() {
		return mutableList;
	}

	/**
	 * 
	 * @param mutableList the mutable guessing list
	 */
	public HangmanServiceImpl setMutableList(ArrayDeque<String> mutableList) {
		HangmanServiceImpl.mutableList = mutableList;
		return this;
	}

	/**
	 * 
	 * @return the transient player status model
	 */
	public StatusModel getStatusModel() {
		return StatusModel.getInstance();
	}
	
}
