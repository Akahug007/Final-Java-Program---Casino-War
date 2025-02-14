// Players package
package Players;

// Card class is in the Cards package
import Cards.Card; 

// Player1 interface
interface PlayerInterface
{
    public Card[] getCardDeck();
    public void setCardDeck(int deckSize);
}

// Player1 class, where it is a subclass of the Person class
// Object class for the player/user
public class Player1 extends Person implements PlayerInterface
{
	// Declares an array of integers to represent the card deck
    private Card[] cardDeck;

    // Constructor of Player1 class
    public Player1(String playerName) 
    {
		super(playerName);
	}
    
    // Method to get the card deck
    public Card[] getCardDeck()
    {
        return cardDeck;
    }

    // Method to set the card deck
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
