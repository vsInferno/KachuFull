package team.B;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Defines a deck and its properties and capabilities.
 * 
 * @author Aum Patel, Archit shah, Shivam Vyas, Vraj Shah @version1.0
 */
public class Deck {

	private ArrayList<Card> cards;
	private Stack<Card> stack = new Stack<>();

	/**
	 * Create an empty deck of cards.
	 */
	public Deck() {
		cards = new ArrayList<>();
	}

	/**
	 * Add cards to a deck.
	 * 
	 * @param card
	 */
	public void createDeck(Card card) {
		cards.add(card);
	}

	/**
	 * Shuffle the deck to arrange cards in random order.
	 */
	public void shuffleDeck() {
		int random = 0;
		Random rn = new Random();

		for (int i = 0; i < 52; i++) {
			random = rn.nextInt(cards.size());
			stack.push(cards.get(random));
			cards.remove(random);
		}
	}

	/**
	 * Distribute a card.
	 * 
	 * @return
	 */
	public Card dealACard() {
		return stack.pop();
	}

	/**
	 * Return the suit and number of all cards in hand
	 */
	public String toString() {
		return String.valueOf(cards);
	}

}