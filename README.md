# Tic Tac Toe 2.0!

## Introduction
[Tic-Tac-Toe](https://en.wikipedia.org/wiki/Tic-tac-toe) is a traditional 2-player board game. However what if your friends want to join? 

Tic-Tac-Toe 2.0 breaks the 2 player limit and allows upto 10 players to play at once!

![tic_tac_toe](https://user-images.githubusercontent.com/77756530/146633313-3b24ecd8-6894-4fba-bac0-d800c4c77bd1.jpg)

## 2 quick rules:
1. Upto 10 players can play the game and each player choose their own character
1. Number of characters to match along a row, column, or diagonal range from 3 to number of players minus 1.


## Details
* Object-oriented design simplifies the implementation of the game to break different functionalities into their own class
  * Board Class -  defines, stores, designs, & updates the Board based on the current state of the game
  * Player Class - each player has its own moves, character, & name to begin the game with. Lastly, a player has a status - Win or not win(lose or draw).
  * GameLogic - encapsulates all the game logic for the Tic-Tac-Toe 2.0 Game. This allows for other classes to be reused for other Board games. Only the GameLogic for another game replaces the logic in this class and the game is implemented right away!
  * TicTacToeTester - driver class that tests the game.

## Preview

##### 6 Player Board Setup
![main](https://user-images.githubusercontent.com/77756530/146654922-3892b282-4582-4e17-ba6c-64d3a45c4167.jpeg)

##### Let's say 5 in a row, column or diagonal wins
![win](https://user-images.githubusercontent.com/77756530/146654940-e628e01f-bf9c-4b9f-9c2e-2bdf57660708.jpeg)

 
 ## Status
 Completed
 
