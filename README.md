##############################
###########HANGMAN############
##############################

The word to guess is represented by a row of dashes, representing each letter of the word. In most variants, proper nouns, such as names, places, and brands, are not allowed

Game features : 
 - For the purpose of this test, the api DO NOT manage users sessions.
 - Players are able to start a new game.
 - Players are able to see the attempts remaining.
 - Players are able to see the words he already used.
 - Players are able to interact to the game system and guess a character for an ongoing game through a provided web app.


################################################################################################################

 To run this application, you must first build the project with maven, open the console in the project folder and run : 
   - mvnw clean package.

Then start the app with the comand : 

 -  java -jar target/HangmanService-0.0.1-SNAPSHOT.jar

Your api server is ready t run!!

Now you must run the user interface, for that you must have a node server started, after it's started, acess the application by acessing localhost:3000.

HAVE FUN!