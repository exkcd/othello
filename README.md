# Othello (Reversi)

Final project for CSCI-4448 Object-Oriented Analysis and Design. This is an implementation of the popular board game
Othello (Reversi).

## What is it?

From Wikipedia:

The Tragedy of Othello, the Moor of Venice, often shortened to Othello,[a] is a tragedy written by William
Shakespeare around 1603. Set in Venice... Oh, whoops wrong Othello.

The correct Wikipedia definition:

Reversi is a strategy board game for two players, played on an 8×8 uncheckered board. It was invented in 1883. Othello, a variant with a fixed initial setup of the board, was patented in 1971.


## How to play

The game begins with four discs placed in the middle of the board. Black moves first.

![Starting Position](assets/images/starting_position.png)


Black must place a disc on the board with one or more contiguous white pieces between the placed black disc and an existing one.

![Valid first moves](assets/images/valid_first_moves.png)

After placing the disc, your opponents discs in-between the placed disc and the existing disc are all flipped to your color.

![Flip all of your opponents discs in-between yours](assets/images/flipping_discs.png)

Then it is White's move. Same rules apply, but the roles are reversed. The
possibilities for flipping are:

![White's turn](assets/images/white_turn.png)

When a move is selected, all the opposite color discs are then flipped:

![White flips](assets/images/white_flip.png)

This alternates until the game is over when neither player can move.