package Main;

// Imports classes from the Players package
import Players.Dealer;
import Players.Player1;
import java.util.*;

import Cards.Card;
import Players.Person;

// This program is about Casino War
// The game is played with a chosen number of decks with 52 cards each
// This is the driver class where all code executions will happen
public class CasinoWarGame 
{
    // This is the main method where all java code executions will happen
    public static void main(String[] args) 
    {
        // Instantiating the Scanner class
        Scanner input = new Scanner(System.in);

        // Program asks the user to enter an integer value for the size of a deck
        System.out.println("Enter a number of the decks: 1, 2, 4, or 6");

        // Stores the user's input in the variable sizeOfDeck
        int sizeOfDeck = input.nextInt();

        // Conditional statement to check if the size of the deck is 1 or even
        if ((sizeOfDeck == 1 || (sizeOfDeck % 2 == 0))  && sizeOfDeck <= 6)
        {
            sizeOfDeck *= 52;
        }
        else
        {
            sizeOfDeck = 52;
        }

        System.out.println("Size of the deck: " + sizeOfDeck);
        
        // Adds a buffer to the scanner
        input.nextLine();
        System.out.println();

        // Program asks the user to enter a name for the player
        System.out.println("Enter your name: ");

        // Stores the user's input in the variable playerName
        String playerName = input.nextLine();
        System.out.println();

        // Instantiates the Player1 class with the name thePlayer
        Player1 thePlayer = new Player1(playerName);
        thePlayer.setCardDeck(sizeOfDeck);

        // Instantiates the Dealer class with the name theDealer
        Dealer theDealer = new Dealer("Dealer");
        theDealer.setCardDeck(sizeOfDeck);

        // Boolean variable for the playAgain option
        boolean playAgain = true;

        // Output statement that outputs a message to the user/player if they are ready to play, no conditional statement
        // This program would force the player to play the game
        System.out.println("Are you ready to play?");
        input.nextLine();

        // Output statement that outputs a message to welcome the player to the game
        System.out.println();
        System.out.println("Welcome to Casino War!");

        // Game loop
        while (playAgain)
        {
            boolean gameLoop = true;
            // Program informs user of the player's bet amount
            System.out.println();
            System.out.println("Your current balance is: " + thePlayer.getBalance());
                
            // Program asks the user to enter a bet amount
            System.out.println("Enter a bet amount: ");
            
            // Temporary variable to store the user's input
            int playerBetAmount = input.nextInt();

            // Temporary variable to store the dealer's bet amount
            int dealerBetAmount = playerBetAmount;

            while(gameLoop)
            {
                // Boolean variables
                boolean playerWins = false;
                boolean dealerWins = false;

                // Adds a buffer to the scanner
                input.nextLine();

                // Conditional statement
                // If the player's bet amount is greater than the player's balance
                // Bet amount is divided by 2
                if (playerBetAmount > thePlayer.getBalance())
                {
                    System.out.println("Your bet amount is greater than your balance. All bets split in half.");
                    playerBetAmount /= 2;
                    dealerBetAmount = playerBetAmount;       
                }

                // If the player's bet amount is greater than the dealer's balance
                // Bet amount is divided by 2
                else if (dealerBetAmount > theDealer.getBalance())
                {
                    System.out.println("Your bet amount is greater than the Dealer's balance. All bets split in half.");
                    dealerBetAmount /= 2;
                    playerBetAmount = dealerBetAmount; 
                }
                
                // Program informs user of the beginning of the game
                System.out.println("\nPlayer is ready, Dealer is ready ");

                // Program questions user if they are ready to flip their card
                // Will continue without any conditional statement
                System.out.println("Are you ready to flip your card? ");
                input.nextLine();

                System.out.println();

                // Temporary variables that store references to the player's and dealer's card deck
                Card[] playerTempDeck = thePlayer.getCardDeck();
                Card[] dealerTempDeck = theDealer.getCardDeck();    
                System.out.println("Number of cards in your current deck: " + getLastIndex(playerTempDeck));
                System.out.println();

                // Informs the player what card they drew
                System.out.println("You drew a: " + (playerTempDeck[0].getCardName()) + " card");

                // Informs the player what card the dealer drew
                System.out.println("The dealer drew a: " + (dealerTempDeck[0].getCardName()) + " card");

                // Conditional statement to check if the first element has null
                if (playerTempDeck[0] == null || dealerTempDeck[0] == null) 
                {
                    moveElements(dealerTempDeck);
                    moveElements(playerTempDeck);
                }

                // Conditional statement to check the values between the dealer's and player's card
                if (playerTempDeck[0].getCardValue() > dealerTempDeck[0].getCardValue())
                {
                    System.out.println("You win the round!");
                    playerTempDeck[getFirstEmptyIndex(playerTempDeck)] = dealerTempDeck[0];
                    playerTempDeck[getFirstEmptyIndex(playerTempDeck)] = playerTempDeck[0];
                    moveElements(dealerTempDeck);
                    moveElements(playerTempDeck);
                }
                else if (playerTempDeck[0].getCardValue() == dealerTempDeck[0].getCardValue())
                {
                    System.out.println("WAR");
                    System.out.println("Would you like to continue?");
                    input.nextLine();
                    startWar(playerTempDeck, dealerTempDeck);
                }
                else if (playerTempDeck[0].getCardValue() < dealerTempDeck[0].getCardValue())
                {
                    System.out.println("You lost the round!");
                    dealerTempDeck[getFirstEmptyIndex(dealerTempDeck)] = playerTempDeck[0];
                    dealerTempDeck[getFirstEmptyIndex(dealerTempDeck)] = dealerTempDeck[0];
                    moveElements(dealerTempDeck);
                    moveElements(playerTempDeck);
                }
                shuffleArray(playerTempDeck, dealerTempDeck);
                System.out.println();

                // Condition to see if one of the players has the full number of cards
                if (getLastIndex(playerTempDeck) == sizeOfDeck)
                {
                    playerWins = true;
                }
                else if (getLastIndex(dealerTempDeck) == sizeOfDeck)
                {
                    dealerWins = true;
                }

                // Program questions user if they are ready to flip their card
                // Will continue without any conditional statement
                System.out.println("Would you like to continue?");
                input.nextLine();

                // Conditional that checks if the boolean variable playerWins is true
                if (playerWins)
                {
                    System.out.println("You win the round!");
                    System.out.println("You've gained $" + dealerBetAmount + "!");
                    thePlayer.addBalance(dealerBetAmount);
                    theDealer.deductBalance(dealerBetAmount);
                    gameLoop = false;
                }
                // Conditional that checks if the boolean variable dealerWins is true
                else if (dealerWins)
                {
                    System.out.println("You lost the round! The Dealer wins!");
                    System.out.println("You've losted $" + playerBetAmount + "!");
                    thePlayer.deductBalance(playerBetAmount);
                    theDealer.addBalance(playerBetAmount);
                    gameLoop = false;
                }
            }
            // Conditional that checks the game can be played again if both the player and dealer still have money
            if (thePlayer.getBalance() > 0 && theDealer.getBalance() > 0)
            {
                // If true, the program will the prompt the user if they want to play again
                System.out.println("Would you like to play another round? Yes or No");
                String redo = input.nextLine();
                // If true, then the game restarts
                if (redo.equalsIgnoreCase("Yes"))
                {
                    playAgain = true;
                    thePlayer.setCardDeck(sizeOfDeck);
                    theDealer.setCardDeck(sizeOfDeck);
                }
                // If false, then the game stops
                else
                {
                    playAgain = false;
                    System.out.println("The game has ended. Results:");
                    // If true, the program states the player has won
                    if (thePlayer.getBalance() > theDealer.getBalance())
                    {
                        System.out.println("You have won the game!");
                    }
                    // If true, the program states the player has tied
                    else if (theDealer.getBalance() == thePlayer.getBalance()) 
                    {
                        System.out.println("This game ends in a tie!");
                    }
                    // If all conditionals are false, the program states the player has lost the game
                    else
                    {
                        System.out.println("You have lost the game!");
                    }
                }
            }
            else
            {
                // If false, then the game will stop and more conditionals start
                playAgain = false;
                System.out.println("The game has ended. Results:");
                // If true, the program states the player has won
                if (thePlayer.getBalance() > theDealer.getBalance())
                {
                    System.out.println("You have won the game!");
                }
                // If true, the program states the player has tied
                else if (theDealer.getBalance() == thePlayer.getBalance()) 
                {
                    System.out.println("This game ends in a tie!");
                }
                // If all conditionals are false, the program states the player has lost the game
                else
                {
                    System.out.println("You have lost the game!");
                }
            }
        }
    }
    // Method that moves elements in the array to the left 1 time
    // This is to simulate the removal of the card from the deck
    public static void moveElements(Card[] arr)
    {
        for (int i = 1; i < arr.length; i++)
        {
            arr[i - 1] = arr[i];
        }
    }
    // Method that traverses through an array and returns the first empty index
    public static int getFirstEmptyIndex(Card[] arr)
    {
        int index = 0;
        for (int i = 1; i < arr.length; i++)
        {
            if ((arr[i] == null) && arr[i - 1] != null)
            {
                index = i;
                i = arr.length;
            }
        }
        return index;
    }
    // Method that traverses through an array and returns the index of the last non-null element
    public static int getLastIndex(Card[] arr)
    {
        int index = 0;
        for (int i = 1; i < arr.length; i++)
        {
            if (arr[i] != null)
            {
                index = i + 1;
            }
        }
        return index;
    }
    // Debug method that traverses through an array and prints out the card names
    public static void debugArray(Card[] arr1, Card[] arr2)
    {
        // Player's card deck traversing
        System.out.println("Your card deck: ");
        int j = 0;
        for (int i = 0; i < arr1.length; i++)
        {
            if (arr1[i] == null)
            {
                i = arr1.length;
            }
            else
            {
                j = i;
                System.out.print(arr1[j].getCardName() + ", ");
            }
            
        }
        System.out.println();
        // Dealer's card deck traversing
        System.out.println("Dealer's card deck: ");
        int k = 0;
        for (int i = 0; i < arr2.length; i++)
        {
            if (arr2[i] == null)
            {
                i = arr1.length;
            }
            else
            {
                k = i;
                System.out.print(arr2[k].getCardName() + ", ");
            }
            
        }
        System.out.println();       
    }
    // Method to shuffle the array
    public static void shuffleArray(Card[] arr1, Card[] arr2)
    {
        Card temp1;
        Card temp2;
        // 1st Array Shuffle
        for (int i = 0; i < getLastIndex(arr1); i++)
        {
            int randomNumber = (int)(Math.random()*getLastIndex(arr1));
            temp1 = arr1[i];
            temp2 = arr1[randomNumber];
            arr1[randomNumber] = temp1;
            arr1[i] = temp2;
        }
        // 2nd Array Shuffle
        for (int i = 0; i < getLastIndex(arr2); i++)
        {
            int randomNumber = (int)(Math.random()*getLastIndex(arr2));
            temp1 = arr2[i];
            temp2 = arr2[randomNumber];
            arr2[randomNumber] = temp1;
            arr2[i] = temp2;
        }
    }
    // War Method
    public static void startWar(Card[] arr1, Card[] arr2)
    {
        // Same conditional as in the while loop within the main method
        int index = 3;
        boolean war = true;
        // While loop that starts the war game
        while(war)
        {
            // Conditonals
            // Player gets 4 more cards if they win War
            if (arr1[index].getCardValue() > arr2[index].getCardValue())
            {
                System.out.println("You win the War round!");
                for (int i = 0; i < index; i++)
                {
                    arr1[getFirstEmptyIndex(arr1)] = arr2[i];
                    arr1[getFirstEmptyIndex(arr1)] = arr1[i];
                    moveElements(arr2);
                    moveElements(arr1);
                }
                war = false;
            }
            // Player loses 4 cards if they lose War
            else if (arr1[index].getCardValue() < arr2[index].getCardValue())
            {
                System.out.println("You lose the War round!");
                for (int i = 0; i < index; i++)
                {
                    arr2[getFirstEmptyIndex(arr2)] = arr1[i];
                    arr2[getFirstEmptyIndex(arr2)] = arr2[i];
                    moveElements(arr2);
                    moveElements(arr1);
                }
                war = false;
            }
            // If the war ends with the same cards, then the index increments and the game starts again
            else
            {
                System.out.println("You and the Dealer tied the War round!");
                index += 4;
            }
        }
    }
}