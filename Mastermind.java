package application;

import java.util.ArrayList;
import java.util.Random;

/**
 * Mastermind class creates a mastermind game for the user to play. It creates
 * an answer or allows for one to be made, and checks if answer and guesses are
 * valid. This class also calculates the number of black and white pegs in a
 * guess.
 * 
 * @author cadejohnson
 * @version 12/10/19
 */
public class Mastermind {

	/** create answer attribute */
	private String answer;
	
	/** create guess attribute */
	private String guess;
	
	/** create guessList attribute to store/access previous guesses*/
	private ArrayList<String> guessList;
	
	/** create black pegs attribute */
	private int blackPegs;
	
	/** create white pegs attribute */
	private int whitePegs;
	
	/** create black peg list attribute to store/access black pegs*/
	private ArrayList<Integer> blackPegList;
	
	/** create white peg list attribute to store/access white pegs*/
	private ArrayList<Integer> whitePegList;
	
	/** create guesss number attribute */
	private int guessNumber;
	
	/** create attribute to use to find white pegs */
	private String nonBlackPegAnswer;
	
	/** create attribute to use to find white pegs */
	private String nonBlackPegGuess;

	/**
	 * Mastermind constructor initializes instance variables
	 * that will be used throughout the class
	 */
	public Mastermind() {
		answer = new String();
		nonBlackPegAnswer = "";
		nonBlackPegGuess = "";
		guessNumber = 0;
		blackPegs = 0;
		whitePegs = 0;
		guessList = new ArrayList<String>();
		blackPegList = new ArrayList<Integer>();
		whitePegList = new ArrayList<Integer>();
	}
	/**
	 * This method checks if a guess is valid, and then initializes it
	 * and adds it to the guessList
	 * @param guess
	 * @throws Exception if guess isn't valid
	 */
	public void createValidGuess(String guess) throws Exception {
		checkValidCode(guess);
		// made it this far, meaning the guess is valid and we can initialize the guess
		this.guess = guess;
		guessList.add(guess);
	}
	/**
	 * This method takes in a String (which could be the user generated answer, or the guess)
	 * and checks to see if it is valid
	 * @param code
	 * @throws Exception if code length isn't 4 chars, or code doesn't involve all colors
	 */
	public void checkValidCode(String code) throws Exception {
		if (code.length() != 4) {
			//if we are here, the code length isn't 4
			throw new Exception("Invalid: You must choose 4 colors to create a valid code");
		}
		for (int i = 0; i < code.length(); i++) {
			if (code.charAt(i) == 'R' || code.charAt(i) == 'B' || code.charAt(i) == 'G' || code.charAt(i) == 'Y'
					|| code.charAt(i) == 'O' || code.charAt(i) == 'P') {
			} else {
				//if we are here, the code must contain a character that isn't one of the six colors
				throw new Exception("Invalid: Can only contain combination of letters R,B,G,Y,O,P");
			}
		}
	}
	/**
	 * setter method for answer 
	 * @param answer
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/** 
	 * This method auto-generates an answer for the game
	 * and adds it to the answer attribute
	 */
	public void createAnswer() {
		//answer is 4 chars long
		for (int i = 0; i < 4; i++) {
			Random r = new Random();
			int c = (r.nextInt(6));
			//now check indidividually what the number is, and correspond it to the right color
			if (c == 0) {
				char red = 'R';
				answer = answer + red;
			} else if (c == 1) {
				char blue = 'B';
				answer = answer + blue;
			} else if (c == 2) {
				char green = 'G';
				answer = answer + green;
			} else if (c == 3) {
				char yellow = 'Y';
				answer = answer + yellow;
			} else if (c == 4) {
				char orange = 'O';
				answer = answer + orange;
			} else if (c == 5) {
				char purple = 'P';
				answer = answer + purple;
			}
		}
	}
	/**
	 * This method checks for black pegs and 
	 * sets the attribute to the number of black 
	 * pegs found
	 */
	public void checkBlackPegs() {
		for (int i = 0; i < guess.length(); i++) {
			//if guess char equals answer char, add a black peg
			if (guess.charAt(i) == answer.charAt(i)) {
				blackPegs++;
			} else {
				//otherwise, create strings that we know don't have black pegs
				nonBlackPegAnswer = nonBlackPegAnswer + answer.charAt(i);
				nonBlackPegGuess = nonBlackPegGuess + guess.charAt(i);
			}
		}
		//add black pegs number to list
		blackPegList.add(blackPegs);
	}
	/**
	 * This method checks for white pegs and 
	 * sets the attribute to the number of white
	 * pegs found
	 */
	public void checkWhitePegs() {
		int j = 0;
		//first loop through the guess 
		while (j < nonBlackPegGuess.length()) {
			//create temporary holders for our new answers and guesses if a peg is found
			String tempNewAnswer = "";
			String tempNewGuess = "";
			String currentChar = "" + nonBlackPegGuess.charAt(j);
			// check if currentChar is in answer
			if (nonBlackPegAnswer.contains(currentChar)) {
				// if so, then navigate to that char in the answer
				for (int k = 0; k < nonBlackPegAnswer.length(); k++) {
					if (nonBlackPegAnswer.charAt(k) == nonBlackPegGuess.charAt(j)) {
						//add to white pegs
						whitePegs++;
						//now we need to update our new answers and guesses to remove the peg we just found
						for (int i = 0; i < nonBlackPegGuess.length(); i++) {
							//print out the guess and answer except for the char with the peg we found
							if (i != k) {
								tempNewAnswer = tempNewAnswer + nonBlackPegAnswer.charAt(i);
							}
							if (i != j) {
								tempNewGuess = tempNewGuess + nonBlackPegGuess.charAt(i);
							}
						}
						//update our attributes
						nonBlackPegAnswer = tempNewAnswer;
						nonBlackPegGuess = tempNewGuess;
						k = nonBlackPegAnswer.length();
				}
				}
			}else {
				//otherwise, currentChar is not in the answer, so iterate 
				j++;
			}
			//special case check if length is 1 and are white pegs
			if (nonBlackPegGuess.length() == 1 && nonBlackPegAnswer.length() == 1) {
				if (nonBlackPegGuess.equals(nonBlackPegAnswer)) {
					//add to white pegs
					whitePegs++;
				}
				//end loop
				j = 10;
			}
		}
		//add white pegs number to our list
		whitePegList.add(whitePegs);
	}
	
	/**
	 * This method simply resets all guess variables 
	 * back to original values to be ready for the 
	 * next guess
	 */
	public void resetGuess() {
		whitePegs = 0;
		blackPegs = 0;
		guessNumber++;
		nonBlackPegAnswer = "";
		nonBlackPegGuess = "";
	}
	/**
	 * getter method for guess
	 * @return guess
	 */
	public String getGuess() {
		return guess;
	}
	/**
	 * getter method for answer
	 * @return answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * getter method for guess number
	 * @return guess number
	 */
	public int getGuessNumber() {
		return guessNumber+1;
	}
	/**
	 * getter method for black pegs
	 * @return black pegs
	 */
	public int getBlackPegs() {
		return blackPegs;
	}
	/** 
	 * getter method for white pegs
	 * @return white pegs
	 */
	public int getWhitePegs() {
		return whitePegs;
	}
	/**
	 * getter method for black peg list
	 * @return blackPegList
	 */
	public ArrayList<Integer> getBlackPegList() {
		return blackPegList;
	}
	/**
	 * getter method for white peg list
	 * @return whitePegList
	 */
	public ArrayList<Integer> getWhitePegList() {
		return whitePegList;
	}
	/**
	 * getter method for guessList
	 * @return guessList
	 */
	public ArrayList<String> getGuessList() {
		return guessList;
	}
}
