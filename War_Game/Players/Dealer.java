package Players;

import Cards.Card;

// Dealer interface
interface DealerInterface
{
    public Card[] getCardDeck();
    public void setCardDeck(int deckSize);
}

// Dealer class, where it is a subclass of the Person class
// Object class for the dealer/computer
public class Dealer extends Person implements DealerInterface
{
    // Declares an array of integers to represent the card deck
    private Card[] cardDeck;
    
    // Constructor of Dealer class
    public Dealer(String dealerName) 
    {
		super(dealerName);
	}

    // Getter method that returns the reference to the card deck array
    public Card[] getCardDeck() 
    {
        return cardDeck;
    }

    // Setter method that sets the card deck size
    public void setCardDeck(int deckSize) 
    {
        // Instantiates the cardDeck array with the size of the deck
        cardDeck = new Card[deckSize];
        
        // Uses a for loop that iterates through half of the deck size
        // Where it instantiates a new card object and sets the card value
        for (int i = 0; i < (int)(deckSize/2); i++)
        {
            cardDeck[i] = new Card();
            cardDeck[i].setCardValue();
        }
    }
}
