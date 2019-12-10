package application;
import java.util.Scanner;
/**
 * Driver class creates a Mastermind object and allows
 * for user interaction with the game
 * User will guess code until he/she wins or loses
 * @author cadejohnson
 * @version 12/10/19
 */
public class Driver {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println(
				"Welcome to Mastermind! The available colors to guess from are Red, Blue, Green, Yellow, Orange, and Purple \n"
						+ "You have 10 chances to guess the correct code which is a 4 color combination of any of the colors \n"
						+ "Enter 0 if you want the computer to generate the code, or 1 if you want to enter the code yourself");
		int gameType = input.nextInt();
		int check = 0;
		while (check == 0) {
			try {
				Mastermind game = new Mastermind();
				if (gameType == 1) {//user wants to make their own code
					System.out.println("Enter the code in a four letter format (GRBY for example)");
					String userAnswer = input.next();
					game.checkValidCode(userAnswer); //check if it's valid
					game.setAnswer(userAnswer); //then set it as answer
					check = 1;
				} else {
					//otherwise auto-generate code
					game.createAnswer();
					check = 1;
				}
				//this loop will continue until user wins or loses
				while (game.getGuessNumber() <= 10) {
					try {
							System.out.println("Previous guesses: ");
							//create loop to print previous guesses with pegs
							for (int i = 0; i < game.getGuessList().size(); i++) {
								System.out.print(
										game.getGuessList().get(i) + " Black pegs: " + game.getBlackPegList().get(i)
												+ " White pegs: " + game.getWhitePegList().get(i) + "\n");
							}
						System.out.println("Please enter guess #" + game.getGuessNumber()
								+ " in letter format (RBGY for example)");
						String guess = input.next().toUpperCase();
						game.createValidGuess(guess); //check for valid guess
						game.checkBlackPegs(); //check for black pegs
						game.checkWhitePegs(); // check for white pegs
						if (game.getBlackPegs() == 4) { //user has won
							int score = 10 - game.getGuessNumber();
							System.out.println("Congrats! You won! Your final score is " + score);
							break;
						} else if (game.getGuessNumber() == 10) { // user has lost
							System.out.println("End of Game: You lost \n The solution was " + game.getAnswer());
							break;
						}
						game.resetGuess(); //reset guess variables
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		input.close();
	}

}
