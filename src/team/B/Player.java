package team.B;

import java.util.ArrayList;

/**
 * Defines the player class, and its capabilities and properties.
 * 
 * @author Vraj Shah, Aum Patel, Archit Shah, Shivam Vyas
 *
 */
public class Player {

	private String name;
	private int totalScore;
	private int roundScore;
	private int bet;
	private ArrayList<Card> hand = new ArrayList<>();

	/**
	 * Create a player with initial scores of 0.
	 */
	public Player() {
		totalScore = 0;
		roundScore = 0;
	}

	/**
	 * Create a player with initial score of 0 and name.
	 * 
	 * @param name
	 */
	public Player(String name) {

		totalScore = 0;
		roundScore = 0;
		this.name = name;

	}

	/**
	 * Set a player's name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return the name of a player.
	 * 
	 * @return
	 */
	public String getName() {

		return name;

	}

	/**
	 * Set the total score for a player.
	 * 
	 * @param totalScore
	 *            - Player's total score
	 */
	public void setTotalScore(int totalScore) {

		this.totalScore = totalScore;

	}

	/**
	 * Return the total score for a player.
	 * 
	 * @return
	 */
	public int getTotalScore() {
		return totalScore;
	}

	/**
	 * Return the round's total score.
	 * 
	 * @return
	 */
	public int getRoundScore() {
		return roundScore;
	}

	/**
	 * Set the round's score for a player.
	 * 
	 * @param score
	 *            - Round's score
	 */
	public void setRoundScore(int score) {
		this.roundScore = score;
	}

	/**
	 * Assign cards to a player.
	 * 
	 * @param card
	 *            - Card with a suit & number
	 */
	public void setHand(Card card) {
		hand.add(card);
	}

	/**
	 * Return the names of the cards in a player's hand.
	 * 
	 * @return
	 */
	public String getHand() {
		return String.valueOf(hand);
	}

	/**
	 * Return the card's in a player's hand.
	 * 
	 * @return
	 */
	public ArrayList<Card> getHandList() {

		return hand;

	}

	/**
	 * Set the bet of a player.
	 * 
	 * @param bet
	 */
	public void setBet(int bet) {
		this.bet = bet;
	}

	/**
	 * Return the bet of a player.
	 * 
	 * @return
	 */
	public int getBet() {
		return bet;
	}

	/**
	 * Return the amount of cards in a player's hand.
	 * 
	 * @return
	 */
	public int getHandSize() {
		return hand.size();
	}

	/**
	 * Remove a card from a player's hand.
	 * 
	 * @param card
	 */
	public void removeCard(Card card) {

		hand.remove(card);

	}

	/**
	 * Return a card of a specific value.
	 * 
	 * @param num
	 * @return
	 */
	public Card getCard(int num) {

		return hand.get(num - 1);

	}

	/**
	 * Return the highest card in a player's hand.
	 * 
	 * @param hand
	 *            - cards in a player's hand
	 * @return
	 */
	public static Card highestCard(ArrayList<Card> hand) {

		int higherNum = 0;
		Card Highest = new Card();
		for (Card card : hand) {

			if (card.getNumber() > higherNum || card.getNumber() == 1) {

				if (card.getNumber() == 1) {

					Highest = card;
					return Highest;

				} else {

					higherNum = card.getNumber();
					Highest = card;

				}

			}

		}

		return Highest;

	}

	/**
	 * Return the lowest card in a player's hand.
	 * 
	 * @param hand
	 *            - cards in a player's hand.
	 * @return
	 */
	public static Card lowestCard(ArrayList<Card> hand) {
		int lowerNum = 14;
		Card Lowest = new Card();
		for (Card card : hand) {
			if (card.getNumber() < lowerNum) {
				if (card.getNumber() == 1 && hand.size() == 1) {
					return card;
				} else if (card.getNumber() == 1) {

				} else {
					lowerNum = card.getNumber();
					Lowest = card;
				}
			}
		}
		return Lowest;
	}

	/**
	 * Return a player's bet.
	 * 
	 * @param dominant
	 *            - trump suit
	 * @return
	 */
	public int bet(Suit dominant) {
		int bet = 0;
		for (Card card : hand) {
			if (card.getSuit() == dominant) {
				bet++;
			}
		}
		return bet;
	}

	/**
	 * Place an ideal card depending on the trump suit and cards in hand.
	 * 
	 * @param mainSuit
	 *            - Suit placed by first player
	 * @param dominant
	 *            - Trump suit
	 * @return
	 */
	public Card makeMove(Suit mainSuit, Suit dominant) {
		if (mainSuit == Suit.None) {
			return highestCard(this.hand);
		} else {
			for (Card card : hand) {
				if (card.getSuit() == mainSuit) {
					return card;
				}
			}
		}

		for (Card card : hand) {
			if (card.getSuit() == dominant) {
				return card;
			}
		}

		Card fish = new Card();
		fish = lowestCard(this.hand);
		return fish;

	}

}