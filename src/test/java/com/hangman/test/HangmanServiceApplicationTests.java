package com.hangman.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hangman.exception.HangmanException;
import com.hangman.model.CharacterCheck;
import com.hangman.model.StatusModel;
import com.hangman.service.HangmanService;
import com.hangman.service.HangmanServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HangmanServiceApplicationTests {

    @TestConfiguration
    static class HangmanServiceImplTestContextConfiguration {
  
        @Bean
        public HangmanService employeeService() {
            return new HangmanServiceImpl();
        }
        
    }
	
	@Autowired
	private HangmanService hangmanService;
	
	@Test
	public void getNextWordFailure() {
		((HangmanServiceImpl)hangmanService).setMutableList(null);
		assertThat(hangmanService.getNextWord()).isEqualTo(null);
	}
	
	@Test(expected = HangmanException.class)
	public void getCheckCharacterFailure() {
		((HangmanServiceImpl)hangmanService).setMutableList(null);
		hangmanService.checkCharacter('L');
	}
	
	@Test(expected = HangmanException.class)
	public void getCheckCharacterLetterFailure() {
		hangmanService.newGame();
		hangmanService.checkCharacter('1');
	}
	
	@Test
	public void getCheckCharacterNumberUsed() {
		hangmanService.newGame();
		CharacterCheck chk = hangmanService.checkCharacter('L');
		chk = hangmanService.checkCharacter('B');
		chk = hangmanService.checkCharacter('U');
		
		assertThat(chk.getCharacterUsed().size()).isEqualTo(3);
	}
	
	@Test
	public void newGameSucess( ) {
		assertThat(hangmanService.newGame().getCurrentWord().length()).isGreaterThan(0);
	}
	
	@Test
	public void getNextWordSuccess() {
		hangmanService.newGame();
		assertThat(hangmanService.getNextWord().getCurrentWord().length()).isGreaterThan(0);
	}
	
	@Test
	public void getCheckCharacterSuccess() {
		hangmanService.newGame();
		assertThat(hangmanService.checkCharacter('L')).isInstanceOf(CharacterCheck.class);
	}
	
	@Test(expected = HangmanException.class)
	public void getCheckDoubleCharacter() {
		hangmanService.newGame();
		hangmanService.checkCharacter('L');
		hangmanService.checkCharacter('L');
	}
	
	@Test(expected = HangmanException.class)
	public void getCheckOnlyLetters() {
		hangmanService.newGame();
		hangmanService.checkCharacter('8');
	}
	
	@Test
	public void getCheckGameOver() {
		hangmanService.newGame();
		CharacterCheck chk = null;
		char[] letters = {'Z','B','H','Y','W','X','K','H','Q','P'};
		
		for (int i = 0; i < StatusModel.getInstance().getMaxAttempts(); i++) {
			chk = hangmanService.checkCharacter(letters[i]);
		}
		
		assertThat(chk.isGameOver()).isEqualTo(true);
	}

}
