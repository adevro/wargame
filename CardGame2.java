

public class CardGame2 {
    public static void main(String[] args) {
        String cardtype[] = {"H", "S", "D", "C"};
        String cardnumb[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        String cards[] = new String[52];
        int index = 0;
        // Fill the cards array
        for (int i = 0; i < cardtype.length; i++) {
            for (int j = 0; j < cardnumb.length; j++) {
                cards[index] = cardnumb[j] + cardtype[i];
                index++;
            }
        }

        // Shuffle the cards using Fisher-Yates algorithm with Math.random()
        for (int i = cards.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1)); // Generate a random index between 0 and i

            // Swap cards[i] and cards[j]
            String temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }

        // Create two decks for the players
        String deck1[] = new String[52];
        String deck2[] = new String[52];
        int decksindex = 0;

        // Distribute cards into two decks
        for (int i = 0; i < cards.length; i += 2) {
            deck1[decksindex] = cards[i];
            deck2[decksindex] = cards[i + 1];
            decksindex++;
        }

        // Call the iterations method and pass the two decks
        int totalRounds = iterations(deck1, deck2);

        // Output the total rounds played
        System.out.println("Total rounds played: " + totalRounds);
    }

    public static int iterations(String deck1[], String deck2[]) {
    int current = 0; 
    int size1 = 26; // Number of cards in player 1's deck
    int size2 = 26; // Number of cards in player 2's deck
    int rounds = 0;
    int score1 = 0;
    int score2 = 0;


    do {
        // Check if any player has run out of cards
        if (size1 == 0 || size2 == 0) {
            break;
            };

        String card1 = deck1[current];
        String card2 = deck2[current];
        int value1 = getCardValue(card1.charAt(0));
        int value2 = getCardValue(card2.charAt(0));

        // Print the current round details
        System.out.println("Round " + (rounds + 1) + ": " + card1 + " vs " + card2);

        if (value1 > value2) {
            System.out.println("Player 1 wins this round.");
            deck1[size1++] = card1;
            deck1[size1++] = card2;
            score1 = score1 + value1 + value2;
            //System.out.println("Player 1 score: " + score1);
            //System.out.println("Player 2 score: " + score2);
            toggleAce();
            //System.out.println(getCardValue('A')); //to see the new value of the ace if needed, remove the comment
        } else if (value2 > value1) {
            System.out.println("Player 2 wins this round.");
            deck2[size2++] = card1;
            deck2[size2++] = card2;
            score2 = score2 + value1+ value2;
            //System.out.println("Player 1 score: " + score1);
            //System.out.println("Player 2 score: " + score2);
            toggleAce();
            //System.out.println(getCardValue('A')); //to see the value of the ace, remove the comment from the start of the line
        } else { // War condition
            System.out.println("It's a WAR!");

            // Ensure both players have enough cards for war (5 cards)
            if (size1 < 5 || size2 < 5) {
                if (size1 < 5) {
                    System.out.println("Player 1 doesn't have enough cards, they lose!");
                    size1 = 0;
                }
                if (size2 < 5) {
                    System.out.println("Player 2 doesn't have enough cards, they lose!");
                    size2 = 0;
                }
                break; // End the game if a player doesn't have enough cards for war
            }

            // War scenario: Compare the 4th cards after the initial tie
            String warCard1 = deck1[current + 4];
            String warCard2 = deck2[current + 4];
            int warValue1 = getCardValue(warCard1.charAt(0));
            int warValue2 = getCardValue(warCard2.charAt(0));

            System.out.println("War cards: " + warCard1 + " vs " + warCard2);

            if (warValue1 > warValue2) {
                System.out.println("Player 1 wins the war.");
                score1 = score1 + warValue1 + warValue2;
                for (int i = 0; i < 5; i++) {
                    deck1[size1++] = deck1[current + i];
                    deck1[size1++] = deck2[current + i];
                }
            } else if (warValue2 > warValue1) {
                System.out.println("Player 2 wins the war.");
                score2 = score2 + warValue1 + warValue2;
                for (int i = 0; i < 5; i++) {
                    deck2[size2++] = deck2[current + i];
                    deck2[size2++] = deck1[current + i];
                }
            }
        }
        rounds++;
        current++; // Move to the next card for the next round

    } while (size1 > 0 && size2 > 0);

    return rounds;
}

    private static boolean aceIsHighest = true;

    public static int getCardValue(char rank) {
        int value;
        if (rank == 'A') {
            if(aceIsHighest){
                value = 14;
            }else{
                value = 1;
            }
        } else if (rank == 'K') {
            value = 13;
        } else if (rank == 'Q') {
            value = 12;
        } else if (rank == 'J') {
            value = 11;
        } else if (rank == 'T') {
            value = 10;
        } else {
            value = Integer.parseInt(String.valueOf(rank));
        }
        return value;
    }
    public static void toggleAce(){
        aceIsHighest = !aceIsHighest;
    }
}