import java.util.ArrayList;

public class CardGame4 {
    public static void main(String[] args) {
        int wins1 = 0;
        int wins2 = 0;
        int gameNumber = 1;

        while (wins1 < 2 && wins2 < 2) { // Best of 3 (First to 2 wins)
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
            if (deck1.isEmpty()) {
                System.out.println(""); //Formatting, can be changed
                System.out.println("=============================");
                System.out.println("Player 2 wins Game " + gameNumber + "!");
                System.out.println("=============================");
                System.out.println("");
                wins2++;
            } else {
                System.out.println("");
                System.out.println("=============================");
                System.out.println("Player 1 wins Game " + gameNumber + "!");
                System.out.println("=============================");
                System.out.println("");
                wins1++;
            }
            System.out.println("That was " + totalRounds + " rounds!");
            gameNumber++;
        }

        // Announce the overall winner
        System.out.println("");
        System.out.println("=============================");
        System.out.println("");
        if (wins1 > wins2) {
            System.out.println("Player 1 wins the Best of 3 Series!");
        } else {
            System.out.println("Player 2 wins the Best of 3 Series!");
        }
        System.out.println("");
        System.out.println("=============================");
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
        int maxRounds = 100;  // Max, can be changed

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
            } else if (value2 > value1) {
                System.out.println("Player 2 wins this round.");
                System.out.println("");
                deck2.add(card1);
                deck2.add(card2);
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
                String warCard1 = deck1.get(4);
                String warCard2 = deck2.get(4);
                int warValue1 = getCardValue(warCard1.charAt(0));
                int warValue2 = getCardValue(warCard2.charAt(0));

                System.out.println("War cards: " + warCard1 + " vs " + warCard2);

                if (warValue1 > warValue2) {
                    System.out.println("Player 1 wins the forced war.");
                    for (int i = 0; i < 5; i++) {
                        deck1.add(deck1.remove(0));
                        deck1.add(deck2.remove(0));
                    }
                } else {
                    System.out.println("Player 2 wins the forced war.");
                    for (int i = 0; i < 5; i++) {
                        deck2.add(deck2.remove(0));
                        deck2.add(deck1.remove(0));
                    }
                }
                break;  // End the game after forced war
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