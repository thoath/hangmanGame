class HangmanService {
	
  constructor() {
	  this.response = {
		numCharacter:0,
		errorMessage:null,
		characterUsed:[],
		charactersFound:[],
		attempts:0,
		maxAttempts:0,
		isGameOver:null,
		isLastWord:null,
		isWordCompleted:null
	  }  
  };
	
	
  async newGame() {
	  const response = await fetch('/api/newGame');
	  const body = await response.json();
	  
	  if (body.message) {
		  this.response = {
					errorMessage:body.message};
	  } else {
		  this.response = {
				  numCharacter:body.currentWord.length,
				  characterUsed:null,
				  charactersFound:null,
				  attempts:null,
				  maxAttempts:body.maxAttempts,
				  isGameOver:null,
				  isLastWord:null,
				  isWordCompleted:null};
	  }
	  
	  
	  return Promise.resolve(this.response);
  };
  
  async getNewWord() {
	  const response = await fetch('/api/getNextWord');
	  const body = await response.json();
	  
	  if (body.message) {
		  this.response = {
					errorMessage:body.message};
	  } else {
		  this.response = {
					numCharacter:body.currentWord.length,
		    		characterUsed:null,
		    		charactersFound:null,
		    		attempts:null,
		    		isGameOver:null,
		    		isLastWord:null,
		    		isWordCompleted:null};
	  }
	  
	  return Promise.resolve(this.response);
  };
	
  async checkCharacter(character) {
	  
	  const response = await fetch('/api/checkCharacter/'+character,{method:'POST'});
	  const body = await response.json();
	 
	  if (body.message) {
		  this.response = {
					errorMessage:body.message};
	  } else {
		  this.response = {
					numCharacter:body.currentWordLength,
					errorMessage:body.message,
		    		characterUsed:body.characterUsed,
		    		charactersFound:body.charactersFound,
		    		attempts:body.attempts,
		    		isGameOver:body.gameOver,
		    		isLastWord:body.lastWord,
		    		isWordCompleted:body.wordCompleted};
	  }

    
	  return Promise.resolve(this.response);
  };

}

export default HangmanService;