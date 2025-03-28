/* Federico Lizarraga, Dominic Martinez, Natalie Rees
 * CSCE 111 500
 * 03-27-2025
 * 
 * his is a game between 2 players, with each player receiving 26 cards - Natalie
 * 
 * There are 13 cards per card type(diamonds, hearts, clovers, spades), from ace to king. 
 * The smallest value is 2, and the biggest value is A. Each card will have a numerical value. 
 * If it's a number, its value will be the actual number. 
 * If it's a letter T, J, Q, K, or A, its value will be 10, 11, 12, 13, and 14 (or 1), respectively. - Federico
 * 
 * If the cards tie, there is a war. 
 * The players have to place three cards upside down and then flip one card, the highest card wins all cards on the table. 
 * If the cards tie again, repeat the process. If a player runs out of cards, they will lose. - Dominic
 * 
 * Each win will award points and the game will function on a “best out of 3” system. 
 * In order to actually win the game, the player must win all cards twice in two separate games. - Dominic
 * 
 * Each round, the ace will switch from being the highest ranked card (14) to the lowest ranked (1). - Natalie
 * 
 * The players can decide if they want to play 1 round or 3 rounds via the Scanner function. - Federico
 * 
 * At the start of each round, both players flip over the top card of their deck. 
 * Whoever has the higher card wins and places them at the bottom of their deck. 
 * If the cards are the same, each player places three cards facing down, flips a fourth card to determine who the winner is, and takes all the cards on the table. 
 * If there’s another tie, the process repeats until someone wins. The game ends when a player runs out of cards, and the other has all the cards. 
 * To make it more interesting, the overall winner will be the one who wins 2 out of 3 rounds.
 */

import java.util.ArrayList;
import java.util.Scanner;
public class CardGameFinal {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = scnr.next();
        System.out.println("Welcome " + name + "! Do you want to view the game rules?");
        String response = scnr.next();
        if(response.equalsIgnoreCase("yes")){
            System.out.println("RULES:\r\n" + //
            "Game Objective: (How do you win?)\r\n" + //
            "You are going to be given half a deck of shuffled cards. \r\n" + //
            "The game's objective is to get all the cards from the other player.\r\n" + //
            "If you take all the cards you win the round. \r\n" + //
            "The player who wins 2 out of 3 rounds wins (if playing the 3 round version).\r\n" + //
            "\r\n" + //
            "This is a game between 2 players, with each player receiving 26 cards\r\n" + //
            "\r\n" + //
            "There are 13 cards per card type(diamonds, hearts, clovers, spades), from ace to king. \r\n" + //
            "The smallest value is 2, and the biggest value is A. Each card will have a numerical value. \r\n" + //
            "If it's a number, its value will be the actual number. \r\n" + //
            "If it's a letter T, J, Q, K, or A, its value will be 10, 11, 12, 13, and 14 (or 1), respectively.\r\n" + //
            "\r\n" + //
            "If the cards tie, there is a war. \r\n" + //
            "The players have to place three cards upside down and then flip one card, the highest card wins all cards on the table. \r\n" + //
            "If the cards tie again, repeat the process. If a player runs out of cards, they will lose.\r\n" + //
            "\r\n" + //
            "Each win will award points and the game will function on a best out of 3 system. \r\n" + //
            "In order to actually win the game, the player must win all cards twice in two separate games.\r\n" + //
            "\r\n" + //
            "Each round, the ace will switch from being the highest ranked card (14) to the lowest ranked (1).\r\n" + //
            "\r\n" + //
            "The players can decide if they want to play 1 round or 3 rounds via the Scanner function.\r\n" + //
            "\r\n" + //
            "At the start of each round, both players flip over the top card of their deck. \r\n" + //
            "Whoever has the higher card wins and places them at the bottom of their deck. \r\n" + //
            "If the cards are the same, each player places three cards facing down, flips a fourth card to determine who the winner is, and takes all the cards. \r\n" + //
            "If there’s another tie, the process repeats until someone wins. The game ends when a player runs out of cards, and the other has all the cards. \r\n" + //
            "To make it more interesting, the overall winner will be the one who wins 2 out of 3 rounds.");
            System.out.println();
        }else if(response.equalsIgnoreCase("no")){
            System.out.print("");
        }
        System.out.println("How many rounds do you want to play, 1 or 3?");
        int numbergames = scnr.nextInt();
        int wins1 = 0;
        int wins2 = 0;
        int gameNumber = 1;
        while(numbergames!=1 && numbergames!=3) {
            System.out.println("Please a choose a number between 1 and 3: ");
            numbergames = scnr.nextInt();
        }

        while (wins1 < numbergames && wins2 < numbergames) { // Best of 3 (First to 2 wins)
            System.out.println(""); // Formatting
            System.out.println("Starting Game " + gameNumber);

            // Create and shuffle a new deck before each game
            ArrayList<String> deck = createShuffledDeck();

            // Split into two decks
            ArrayList<String> deck1 = new ArrayList<>(deck.subList(0, 26));
            ArrayList<String> deck2 = new ArrayList<>(deck.subList(26, 52));

            // Play one game
            int totalRounds = iterations(deck1, deck2);

            // Determine the winner of this game
            if (deck1.isEmpty()||deck2.size()>deck1.size()) {
                if(numbergames==1){
                    System.out.println(""); //Formatting, can be changed
                System.out.println("=============================");
                System.out.println("Player 2 wins Game ");
                System.out.println("Thank you for playing!");
                System.out.println("=============================");
                System.out.println("");
                wins2++;
                }
                else{
                System.out.println(""); //Formatting, can be changed
                System.out.println("=============================");
                System.out.println("Player 2 wins Game " + gameNumber + "!");
                System.out.println("=============================");
                System.out.println("");
                wins2++;
                }
            } else {
                if(numbergames==1){
                    System.out.println(""); //Formatting, can be changed
                System.out.println("=============================");
                System.out.println("Player 1 wins Game " );
                System.out.println("Thank you for playing!");
                System.out.println("=============================");
                System.out.println("");
                wins1++;
                }
                else{
                System.out.println("");
                System.out.println("=============================");
                System.out.println("Player 1 wins Game " + gameNumber + "!");
                System.out.println("Thank you for playing!");
                System.out.println("=============================");
                System.out.println("");
                wins1++;
                }
            }
            System.out.println("That was " + totalRounds + " rounds!");
            gameNumber++;
        }

        // Announce the overall winner
        if (numbergames == 3) {
            
        System.out.println("");
        System.out.println("=============================");
        System.out.println("");
        if (wins1 > wins2) {
            System.out.println("Player 1 wins the Best of 3 Series!");
            System.out.println("Thank you for playing!");
        } 
        if(wins2>wins1) {
            System.out.println("Player 2 wins the Best of 3 Series!");
            System.out.println("Thank you for playing!");
        }
        System.out.println("");
        System.out.println("=============================");
        }
    }

    // Method to create and shuffle a deck without Collections
    public static ArrayList<String> createShuffledDeck() {
        String[] cardtype = {"H", "S", "D", "C"};
        String[] cardnumb = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        ArrayList<String> deck = new ArrayList<>();

        // Fill the deck
        for (String type : cardtype) {
            for (String number : cardnumb) {
                deck.add(number + type);
            }
        }

        // Fisher-Yates shuffle algorithm
        for (int i = deck.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1)); // Generate a random index between 0 and i

            // Swap deck[i] and deck[j]
            String temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }

        return deck;
    }

    public static int iterations(ArrayList<String> deck1, ArrayList<String> deck2) {
        int rounds = 0;
        int maxRounds = 60;  // Max, can be changed

        while (!deck1.isEmpty() && !deck2.isEmpty()) {
            String card1 = deck1.remove(0);
            String card2 = deck2.remove(0);
            int value1 = getCardValue(card1.charAt(0));
            int value2 = getCardValue(card2.charAt(0));

            System.out.println("Round " + (rounds + 1) + ": " + card1 + " vs " + card2);

            if (value1 > value2) {
                System.out.println("Player 1 wins this round.");
                System.out.println("");
                deck1.add(card1);
                deck1.add(card2);
                toggleAce();
            } else if (value2 > value1) {
                System.out.println("Player 2 wins this round.");
                System.out.println("");
                deck2.add(card1);
                deck2.add(card2);
                toggleAce();
            } else { // War scenario
                System.out.println("It's a WAR!");

                if (deck1.size() < 5 || deck2.size() < 5) {
                    System.out.println("A player doesn't have enough cards for war. Game over.");
                    break;
                }

                String warCard1 = deck1.get(4);
                String warCard2 = deck2.get(4);
                int warValue1 = getCardValue(warCard1.charAt(0));
                int warValue2 = getCardValue(warCard2.charAt(0));

                System.out.println("War cards: " + warCard1 + " vs " + warCard2);

                if (warValue1 > warValue2) {
                    System.out.println("Player 1 wins the war.");
                    System.out.println("");
                    for (int i = 0; i < 5; i++) {
                        deck1.add(deck1.remove(0));
                        deck1.add(deck2.remove(0));
                    }
                } else {
                    System.out.println("Player 2 wins the war.");
                    System.out.println("");
                    for (int i = 0; i < 5; i++) {
                        deck2.add(deck2.remove(0));
                        deck2.add(deck1.remove(0));
                    }
                }
            }

            // See if forced war will occur
            if (rounds >= maxRounds) {
                System.out.println("War tiebreaker triggered after " + maxRounds + " rounds!");

                // Check if either player has fewer than 5 cards
                if (deck1.size() < 5) {
                    System.out.println("Player 1 does not have enough cards for forced war. Player 2 wins!");
                    System.out.println("");
                    deck1.clear(); // Force Player 1 to lose
                    return rounds;
                }

                if (deck2.size() < 5) {
                    System.out.println("Player 2 does not have enough cards for forced war. Player 1 wins!");
                    System.out.println("");
                    deck2.clear(); // Force Player 2 to lose
                    return rounds;
                }

                // Forced War
                if (deck1.size()>deck2.size()){
                    System.out.println("Player 1 wins the game");
                    break;
                }
                else{
                    System.out.println("Player 2 wins the game");
                    break;
                }
            }

            rounds++;
        }
        return rounds;
    }
    private static boolean aceIsHighest = true;

    // Changed to switch for conciseness (Was If Chain)
    public static int getCardValue(char rank) {
        switch (rank) {
            case 'A':
                return aceIsHighest ? 14 : 1;
            case 'K':
                return 13;
            case 'Q':
                return 12;
            case 'J':
                return 11;
            case 'T':
                return 10;
            default:
                return Character.getNumericValue(rank);
        }
    }

    public static void toggleAce() {
        aceIsHighest = !aceIsHighest;
    }
}