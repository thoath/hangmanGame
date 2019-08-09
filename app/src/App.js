import React, { Component } from 'react';
import './App.css';
import HangManService from './components/hangmanService';

class App extends Component {
 
  constructor(props) {
	    super(props);
	    this.hangManService = new HangManService();
	    this.nextWord = this.nextWord.bind(this);
	    this.newGame = this.newGame.bind(this);
	    this.checkCharacter = this.checkCharacter.bind(this);
	    this.handleChange = this.handleChange.bind(this);
	    this.handleInputEnter = this.handleInputEnter.bind(this);
	    this.inputValue = null;
	    this.state = {
    		numCharacter:null,
    		errorMessage:null,
    		characterUsed:null,
    		charactersFound:null,
    		attempts:null,
    		maxAttempts:null,
    		isGameOver:null,
    		isLastWord:null,
    		isWordCompleted:null,
    		inputValue:''
	    }
  }
	
  async componentDidMount() {
	   this.newGame();
  }
  
  async newGame() {
	   this.hangManService.newGame().then(response => {
		   this.setState(response);
		});
  }
  
  async nextWord() {
  	   this.hangManService.getNewWord().then(response => {
  		   this.setState(response);
  		});
  }
  
  async checkCharacter() {
	  if (this.state.inputValue && this.state.inputValue.trim() != '' && this.state.inputValue.length == 1) {
		  this.hangManService.checkCharacter(this.state.inputValue).then(response => {
			  this.setState(response);
		  });
	  } 
   }
  
  handleChange(event) {
	  this.setState({inputValue: event.target.value});
  }
  
  handleInputEnter(event) {
	  this.setState({inputValue:''});
  }
  
  render() {
    const {numCharacter,errorMessage,characterUsed,charactersFound,attempts,maxAttempts,isGameOver,isLastWord,isWordCompleted} = this.state;
    
    let rows = [];
   
    for(let i=0; i<numCharacter; i++){
    	
    	if (charactersFound) {
    		Object.keys(charactersFound).forEach(function(key) {
    			if (key == i ) {
    				rows.push(' '+charactersFound[key]+' ')
    			}
    		});
    	}
    	
    	if (rows[i] == null){
    	  rows.push(' _ ')
    	}
    }
    
    let button;
    let disabled;
    
    if (!isLastWord && isWordCompleted) {
        button = <button id="play" onClick={this.nextWord} > Próxima </button>;
        disabled = true;
    } else if (isGameOver) {
    	disabled = true;
    	button = <span>FIM DE JOGO</span>
    } else if (isLastWord && isWordCompleted) {
    	disabled = true;
    	button = <span>JOGO FINALIZADO PARABÉNS</span>
    }
    
    return (
    		
      <div className="App">
        <header className="App-header">
        	<div class="center">
				<div>
					  <div id="center">
					  		<button id="play" onClick={this.newGame}>NOVO JOGO</button>
			          </div>
			          <br/>
			          <br/>
			          <div id="center">
		          		<input type="text" maxLength="1" style={{width: '60px'}} value={this.state.inputValue}
		      		  		disabled={disabled} onChange={this.handleChange} onClick={this.handleInputEnter} />
		      		  
		          		<button disabled={disabled} id="clear" onClick={this.checkCharacter}>Verifica</button>
				  	  </div>
			          <div id="letters">
				          <span id="word">{rows}</span>
			          </div>
			          <div id="center"><h2>{button}</h2></div>
			          <br/>
			          <div id="error"><h2>{errorMessage}</h2></div>
			          <h3>Tentativas restantes : </h3><span>{maxAttempts - attempts} </span>
			          <h3>Letras usadas : </h3><span>{characterUsed} </span>
				</div>
			</div>
        </header>
      </div>
    );
  }
}

export default App;
