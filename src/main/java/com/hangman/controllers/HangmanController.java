package com.hangman.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hangman.model.CharacterCheck;
import com.hangman.model.StatusModel;
import com.hangman.service.HangmanService;
import com.hangman.service.HangmanServiceImpl;

/**
 * Hangman game api
 * No Session Management
 * @author lucas.luz
 *
 */
@RestController
@RequestMapping("/api")
public class HangmanController {

	@GetMapping(value = "/newGame")
    public StatusModel newGame() {
	  HangmanService hangmanService = new HangmanServiceImpl();
	  
	  return hangmanService.newGame();
	  
    } 
	
  	@GetMapping(value = "/getNextWord")
    public StatusModel getNextWord() {
	  HangmanService hangmanService = new HangmanServiceImpl();
	  
	  return hangmanService.getNextWord();
	  
    }

  	@PostMapping(value = "/checkCharacter/{letter}")
    public CharacterCheck checkCharacter(@PathVariable Character letter) {
	  
  	  HangmanService hangmanService = new HangmanServiceImpl();

	  return hangmanService.checkCharacter(Character.toUpperCase(letter));
	  
    } 
  	
	
}
