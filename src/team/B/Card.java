package team.B;

/**
 * Defines a card and its properties and capabilities.
 * 
 * @author Shivam Vyas, Vraj Shah, Aum Patel, Archit Shah
 *
 */
public class Card {

	private Suit suit;
	private int number;

	/**
	 * Create an empty card with no suit.
	 */
	public Card() {
		this.suit = Suit.None;
		this.number = 0;
	}

	/**
	 * Create a card with a suit and number.
	 * 
	 * @param suit
	 *            - Card's suit
	 * @param number
	 *            - Card's number
	 */
	public Card(Suit suit, int number) {
		this.suit = suit;
		this.number = number;
	}

	/**
	 * Sets the suit of a card.
	 * 
	 * @param suit
	 *            - Card's suit
	 */
	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	/**
	 * Returns the suit of a card.
	 * 
	 * @return
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * Sets the number of a card.
	 * 
	 * @param number
	 *            - Card's number
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Returns the number of a card.
	 * 
	 * @return
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Returns the number and suit of a card.
	 */
	public String toString() {
		return "" + number + " of " + suit.toString();
	}

}