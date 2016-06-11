package team.B;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver is an interactive object oriented program that allows a player to play
 * KachuFull game with 3 AI players and it displays winner for each round and
 * then for the entire game.
 * 
 * @author Shivam Vyas, Archit Shah, Vraj Shah, Aum Patel
 * @version 1.0
 */
public class Driver {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Suit dominant = Suit.None;

		Deck deck = new Deck();

		Player player1 = new Player("You");
		Player player2 = new Player("John");
		Player player3 = new Player("Ron");
		Player player4 = new Player("Harry");

		Player players[] = new Player[4];
		players[0] = player1;
		players[1] = player2;
		players[2] = player3;
		players[3] = player4;

		boolean play = true;
		do {
			for (Player player : players) {
				player.setTotalScore(0);
			}
			System.out.println("Welcome to the KachuFull Game!\n");
			System.out.println(
					"---------------------------------------------------------------------------------------------------");
			System.out.println("Instructions: 1 IS AN ACE, 11 IS A JACK, 12 IS A QUEEN, 13 IS A KING."
					+ "\nAfter the cards are dealt, the player who got the 1st card first will be the "
					+ "\nfirst to predict the hands that he will make after looking at the cards. The trump is spades "
					+ "\nin the first game.  With five cards given to each player, there is a possibility of making a "
					+ "\nmaximum of five hands. All the other players, at their turn (in counterclockwise direction) "
					+ "\nmake their predictions on the number of hands they will make. As an example, assume the 1st "
					+ "\nplayer made the prediction of two hands, Computer 1 of zero hands, Computer 2 of one hand and "
					+ "\nComputer 3 of three hands. The predictions cannot add up to the number of cards dealt. "
					+ "\n\nAfter the predictions are over, the game begins with player who was dealt the 1st card. "
					+ "\n\nSuppose the first player plays the King of Hearts, then all the players either place a random "
					+ "\nsuit or the trump suit. Playing the trump suit card wins over"
					+ "\nall the other suits, but within the trump suit, hierarchy of values still apply. If not "
					+ "\na trump suit, but a random suit is played and no one has the ace of hearts, so the King of hearts "
					+ "\nwins over other cards of hearts and he makes a hand. Each turn, the trump suit changes and cycles "
					+ "\nthrough Spades, Diamond, Clubs, and Hearts.");
			System.out.println(
					"---------------------------------------------------------------------------------------------------");

			int playerTurn = 0;
			for (int gameNum = 1; gameNum < 10; gameNum++) {
				System.out.println("\nGame " + gameNum + " Begins!");
				if (playerTurn >= 4 || playerTurn < 0) {

					playerTurn = 0;

				}
				createDeck(deck);
				deck.shuffleDeck();

				if (gameNum == 1 || gameNum == 5 || gameNum == 9) {

					dominant = Suit.Spades;

				} else if (gameNum == 2 || gameNum == 6) {

					dominant = Suit.Diamond;

				} else if (gameNum == 3 || gameNum == 7) {

					dominant = Suit.Clubs;

				} else {

					dominant = Suit.Hearts;

				}

				if (gameNum <= 5) {

					for (int i = 0; i < gameNum; i++) {

						player1.setHand(deck.dealACard());
						player2.setHand(deck.dealACard());
						player3.setHand(deck.dealACard());
						player4.setHand(deck.dealACard());

					}

				} else {

					for (int i = gameNum - 5; i < 5; i++) {

						player1.setHand(deck.dealACard());
						player2.setHand(deck.dealACard());
						player3.setHand(deck.dealACard());
						player4.setHand(deck.dealACard());

					}

				}

				// Players Start Betting
				int totalBet = 0;
				playerTurn = (gameNum % 4) - 1;
				for (int z = 0; z < 4; z++) {
					if (playerTurn >= 4 || playerTurn < 0) {

						playerTurn = 0;

					}
					if (players[playerTurn].equals(player1)) {

						System.out.println("\nThese are your cards:\n" + player1.getHand() + "\nThe dominant suit is "
								+ dominant + "\n");
						System.out.print("Please Enter Your Bet: ");
						player1.setBet(getBetInput(player1, in));

					} else {

						players[playerTurn].setBet(players[playerTurn].bet(dominant));
						System.out.println(players[playerTurn].getName() + " Bets " + players[playerTurn].getBet());

					}
					totalBet = totalBet + players[playerTurn].getBet();
					System.out.println("Now Total Bet is " + totalBet + "\n");

					if (totalBet == players[playerTurn].getHandSize() && z == 3) {

						totalBet = totalBet - players[playerTurn].getBet();

						players[playerTurn]
								.setBet(validateTotalBet(playerTurn, players[playerTurn].getHandSize(), totalBet));

						System.out.print("Total Bet cannot be equal to Hand Size so " + players[playerTurn].getName()
								+ " is asked to Bet Again!");
						System.out.println(" He Bets " + players[playerTurn].getBet());

						totalBet = totalBet + players[playerTurn].getBet();

					}
					playerTurn++;

				}
				System.out.println("\nTotal Bet: " + totalBet + "\n");

				// Start Playing
				int handSize = player1.getHandSize();
				for (int round = 0; round < handSize; round++) {

					Suit mainSuit = Suit.None;
					Card[] roundCards = new Card[4];
					playerTurn = (gameNum % 4) - 1;
					for (int turn = 0; turn < 4; turn++) {
						Card cardPlaced = new Card();
						if (playerTurn >= 4 || playerTurn < 0) {
							playerTurn = 0;
						}

						if (players[playerTurn].equals(player1)) {
							System.out.println("\nThese are your cards:\n" + player1.getHand()
									+ "\n\nThe dominant suit is " + dominant);
							System.out.print(
									"(Turn #" + (turn + 1) + ") Please Place a Card by pressing numbers 1 - 5: ");

							int cardNum = getCardInput(player1, in);
							cardPlaced = player1.getCard(cardNum);
							player1.removeCard(cardPlaced);

							System.out.println("You placed " + cardPlaced);
						} else {

							String name = players[playerTurn].getName();
							cardPlaced = players[playerTurn].makeMove(mainSuit, dominant);
							System.out.println(name + " places " + cardPlaced);
							players[playerTurn].removeCard(cardPlaced);

						}

						if (turn == 0) {
							mainSuit = cardPlaced.getSuit();
						}
						roundCards[turn] = cardPlaced;
						playerTurn++;
					}

					// selects the best card from the round
					Card bestCard = bestCard(roundCards, mainSuit, dominant);
					String roundWinner = roundWinnerName(playerTurn, roundCards, bestCard, players);
					// METHOD FOR GIVING POINTS TO THE WINNER(S)...
					setRoundPoints(players, roundWinner);

					System.out.println("Winner of this round is " + roundWinner + " with the Card: " + bestCard + "\n");
				}
				givePoints(players);

				for (Player player : players) {

					player.setRoundScore(0);

				}

				playerTurn++;
				System.out.println("Game " + gameNum + " Finished\n\n");

			}
			System.out.println("The Kachuful is finished! :)");
			System.out.println("Here are the Scores: ");
			System.out.println(player1.getName() + " with " + player1.getTotalScore());
			System.out.println(player2.getName() + " with " + player2.getTotalScore());
			System.out.println(player3.getName() + " with " + player3.getTotalScore());
			System.out.println(player4.getName() + " with " + player4.getTotalScore());

			System.out.println("Congratulations " + winner(players) + "! You Won the Game.\n\n");

			play = playAgain(in);

		} while (play);
		in.close();
	}

	/**
	 * At the end of each round, to each player's current score, more points are
	 * incremented if they won the previous round (their predicted bet for that
	 * round was correct).
	 * 
	 * @param players
	 */
	private static void givePoints(Player[] players) {
		for (Player player : players) {
			if (player.getBet() == player.getRoundScore()) {
				player.setTotalScore(player.getTotalScore() + 10);
			}
		}

	}

	/**
	 * Asks user if he/she wishes to play again.
	 * 
	 * @param in
	 * @return
	 */
	public static boolean playAgain(Scanner in) {
		String input = "";
		while (!input.equalsIgnoreCase("yes") || !input.equalsIgnoreCase("yes")) {
			System.out.print("Do you want to play again? ");
			input = in.next();
			if (input.equalsIgnoreCase("yes")) {
				return true;
			} else if (input.equalsIgnoreCase("no")) {
				return false;
			} else {
				System.out.println("\nPlease Enter Only Yes Or No!\n");
			}
		}
		return false;
	}

	/**
	 * Return the game winner's name.
	 * 
	 * @param players
	 *            - Players and their attributes
	 * @return
	 */
	private static String winner(Player[] players) {
		int highest = 0;
		for (Player element : players) {
			if (element.getTotalScore() > highest) {
				highest = element.getTotalScore();
			}
		}

		String winners = "";
		int numberOfWinners = 0;
		for (int i = 0; i < players.length; i++) {
			if (players[i].getTotalScore() == highest) {
				winners = players[i].getName();
				numberOfWinners++;
			}
		}
		System.out.println(numberOfWinners);

		if (numberOfWinners > 1) {
			winners = "";
			for (Player player : players) {
				if (player.getTotalScore() == highest) {
					winners = winners + player.getName() + " ";
				}
			}
			return winners;
		} else {
			return winners;
		}
	}

	/**
	 * Return the round winner's name.
	 * 
	 * @param playerTurn
	 *            - Player's turn number
	 * @param roundCards
	 *            - Cards placed by all players
	 * @param bestCard
	 *            - Ideal card from all cards placed
	 * @param players
	 *            - Players and their attributes
	 * @return
	 */
	private static String roundWinnerName(int playerTurn, Card[] roundCards, Card bestCard, Player[] players) {
		int index = 0;
		for (int i = 0; i < roundCards.length; i++) {
			if (roundCards[i].getNumber() == bestCard.getNumber() && roundCards[i].getSuit() == bestCard.getSuit()) {
				index = i;
			}
		}

		int equation = playerTurn + index;
		if (equation >= 4) {
			equation -= 4;
		}

		return players[equation].getName();
	}

	/**
	 * Return the position of a card in hand that user wishes to place.
	 * 
	 * @param player1
	 *            - The human player
	 * @param in
	 *            - Scanner object
	 * @return
	 */
	private static int getCardInput(Player player1, Scanner in) {
		int cardNum = 0;
		boolean valid = false;
		while (!valid) {
			String temp = in.next();
			try {
				cardNum = Integer.valueOf(temp);
				if ((cardNum > 0) && (cardNum <= player1.getHandSize())) {
					return cardNum;
				} else {
					System.out.print("\nPlease Enter again! \n(For Example: \"2\" for Second card in your Hand):  ");
					valid = false;
				}
			} catch (Exception e) {
				System.out.println("\nPlease Enter again! \n(For Example: \"2\" for Second card in your Hand): ");
			}
		}
		return cardNum;
	}

	/**
	 * Keeps track of number of hands won per round so it can be compared to
	 * their initial bet.
	 */
	private static void setRoundPoints(Player[] players, String roundWinner) {
		for (Player player : players) {
			if (player.getName().equals(roundWinner)) {
				player.setRoundScore(player.getRoundScore() + 1);
			}
		}
	}

	/**
	 * Returns the most ideal card from all cards placed by all players.
	 * 
	 * @param roundCards
	 *            - cards placed by all players
	 * @param mainSuit
	 *            - suit of the first card
	 * @param dominant
	 *            - trump suit
	 * @return
	 */
	private static Card bestCard(Card[] roundCards, Suit mainSuit, Suit dominant) {
		ArrayList<Card> domits = new ArrayList<>();
		ArrayList<Card> mains = new ArrayList<>();

		for (Card card : roundCards) {
			if (card.getSuit() == dominant) {
				domits.add(card);
			} else if (card.getSuit() == mainSuit) {
				mains.add(card);
			}
		}

		if (domits.size() > 0) {
			return Player.highestCard(domits);
		} else {
			return Player.highestCard(mains);
		}

	}

	/**
	 * Validate the player's bet to ensure the game's rules are followed.
	 * 
	 * @param playerTurn
	 *            - Player's turn number
	 * @param handSize
	 *            - Amount of cards in hand
	 * @param totalBet
	 *            - Bet for the round
	 * @return
	 */
	private static int validateTotalBet(int playerTurn, int handSize, int totalBet) {
		Scanner in = new Scanner(System.in);
		if (playerTurn == 0) {
			boolean valid = false;
			int newBet = 0;
			System.out.println("You cannot bet that number as: total bet = hand size");
			while (!valid) {
				System.out.print("Please bet again: ");
				String temp = in.nextLine();
				try {
					newBet = Integer.valueOf(temp);
					if ((newBet >= 0 && newBet <= handSize) && (newBet + totalBet != handSize)) {
						return newBet;
					}
				} catch (Exception e) {
					System.out.println("Please Enter a Valid Input!");
				}
			}
			in.close();
		}
		int bet = handSize - totalBet + 1;
		return bet;
	}

	/**
	 * Create a deck with cards of all suits.
	 * 
	 * @param deck
	 *            - Empty deck to be loaded
	 */
	public static void createDeck(Deck deck) {
		for (int i = 0; i < 52; i++) {

			Suit suit = Suit.None;

			if (i < 13) {

				suit = Suit.Spades;
				deck.createDeck(new Card(suit, i + 1));

			} else if (i < 26) {

				suit = Suit.Diamond;
				deck.createDeck(new Card(suit, i + 1 - 13));

			} else if (i < 39) {

				suit = Suit.Clubs;
				deck.createDeck(new Card(suit, i + 1 - 26));

			} else if (i < 52) {

				suit = Suit.Hearts;
				deck.createDeck(new Card(suit, i + 1 - 39));

			}

		}

	}

	/**
	 * Return the validated bet amount that the user wishes to input.
	 * 
	 * @param player1
	 *            - The human player
	 * @param in
	 *            - Scanner object
	 * @return
	 */
	private static int getBetInput(Player player1, Scanner in) {
		int bet = 0;
		boolean valid = false;
		while (!valid) {
			String temp = in.next();
			try {
				bet = Integer.valueOf(temp);
				if ((bet >= 0) && (bet <= player1.getHandSize())) {

					return bet;

				} else {

					System.out.print("\nPlease Enter again between 0 to " + player1.getHandSize() + " : ");
					valid = false;

				}
			} catch (Exception e) {
				System.out.println("\nPlease Enter again between 0 to " + player1.getHandSize() + " : ");
			}
		}
		return bet;
	}

}