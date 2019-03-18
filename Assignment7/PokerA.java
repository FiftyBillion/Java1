import java.util.*;
import java.io.*;
import java.awt.*;

/**
 * Computer poker game.
 * @author Don
 * @edited by Po-I Wu
 * @version 20170220
 */
public class PokerA {
	
	// Global Constants
	public static final int DECK_SIZE = 52;  // The number of cards in a deck
	public static final int HAND_SIZE = 5;   // The number of cards in a hand
	public static int BANK_ROLL = 100;  	 // This is how much money you start with
	
	// Global variables
	public static int chips = BANK_ROLL;  	
	
	/**
	 * Main program entry point
	 * @param args not used in this program
	 */
	public static void main(String[] args) {
		/*
		 * Cards have values of 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, and Ace
		 * Cards have suits of Diamonds, Spades, Clubs, Hearts
		 * For our purposes we will number the cards 2-53 in order of suits and values
		 * Hearts are 2-14, Spades are 15-27, Diamonds are 28-40, and Clubs are 41-53
		 */
		
		/*
		 * Variables used in the program
		 */
		 
		Scanner in = new Scanner(System.in);
		int [] deck = new int [DECK_SIZE];			// the card deck
		int [] playerHand = new int[HAND_SIZE];		// the user's hand
		int [] computerHand = new int[HAND_SIZE];	// the computer's hand
		int [] playerRank;							// the rank of the user's hand
		int [] computerRank;						// the rank of the computer's hand
		int [] playerScore;							// who won
		boolean playing = true;						// is the game still in play
		int bet;									// the amount of the user's bet
		
		// while the user has money and wants to play
		while (playing) {
			
			// Shuffle the cards - this randomizes the deck of cards
			deck = shuffle();
			
			// Deal the cards - There are two players, the user and the computer.
			// The computer gets 5 cards - The user gets 5 cards.
			// These cards come from the deck.  Start at one end of the deck and take
			// cards from the deck and place  into a hand.  When the card is taken
			// from the deck, it is no longer available (from the deck).  It is now in the hand 
			// of one of the players.
			playerHand = deal(deck);
			computerHand = deal(deck);
			
			// Allow the user to Select new cards - The user is allowed to get rid of 
			// up to four cards in their hand.  These cards are replaced with cards that are coming
			// from the deck, that have not been taken from the deck before.  
			// The computer does not get to discard and replace cards.
			playerHand = discard(in, playerHand, deck);
			
			// Bet  -  Prompt the user to place a bet.  The bet can be any value up to the total
			// amount that the user has left in chips.  The computer does not get to bet. 
			// In this way the user has an advantage over the computer.
			bet = bet(in);
			
			// Display hands - show what each play has in their hand.  The players are the user and the computer.
			System.out.println("Computer");
			display(computerHand);
			System.out.println("Player");
			display(playerHand);
			
		    // Determine winner - This is an interesting process.  Each hand will be evaluated and then
			// it will be compared to the other hand.  The hand with the best ranking will be the winner.
			playerRank = rank(playerHand);
			computerRank = rank(computerHand);
			
			// determine the winner of the hand
			String winner = score(playerRank, computerRank);
			
			/*
			 * report the winner and the winnings
			 */
			if (winner.equals("user")) { 
				chips = chips + bet * 2;
				System.out.println("You won this hand.  You won $" + (bet*2));
			}
			
			// finish playing - If the user has money left in chips and want to continue
			System.out.println("do you wish to continue playing? (Y)/N =>");  // default to continue playing
			if (in.hasNext()) {
				char ch = in.next().charAt(0);
				if (ch == 'N' || ch == 'n') {
					playing = false;
				} else {
					if ( chips == 0 ) {
						playing = false;
						System.out.println(" Sorry, you are out of money and must quit ");
					} else {
						playing = true;
					}
				}
			}			
		}
	}
	
	/**
	 * Shuffle the deck of cards and return to the caller.
	 * Shuffling amounts to placing the 52 cards randomly into the deck
	 * without repeating any of the cards.  Card values are from 2-54
	 * @return the shuffled cards
	 */
	public static int [] shuffle() {
		// The deck is initialize to all 0's
		int [] cards = new int [DECK_SIZE];
		Random r = new Random();
		int count = 0;
		int card = 0;
		boolean duplicate;
		
		// get a new card - The value of r is 0-51. So the value of 
		// the cards must be that number plus 2, or 2-54
		card = r.nextInt(DECK_SIZE) + 2;
		cards[count++] = card;
		duplicate = false;
		do {
			card = r.nextInt(DECK_SIZE) + 2;
			// check to see if that card is used yet
			for (int i = 0; i < count; i++) {
				if (card == cards[i]) duplicate = true;
			}
		
			// if not duplicate, add to deck
			if (!duplicate) {
				cards[count++] = card;
			} else {
				duplicate = false;
			}
			// get a new card
			card = r.nextInt(DECK_SIZE) + 2;
		} while( count < DECK_SIZE );

		return cards;
	}
	/**
	 * Deal the cards from the deck.  Cards dealt are removed from the deck
	 * and placed in the hand. a -1 indicates the card is removed from the deck
	 * @param deck the deck of random cards
	 * @return the hand that is dealt.
	 */
	public static int [] deal (int [] deck) {
		int [] hand = new int [HAND_SIZE];
		for (int i = 0; i < HAND_SIZE; i++) {
			hand[i] = select(deck);
		}
		return hand;
	}
	
	/**
	 * Discard is for the player only where s/he can replace any of the
	 * cards in not wanted in the hand
	 * @param deck the deck of random cards
	 * @return the new hand with the discards replaced
	 */
	public static int [] discard (Scanner in, int [] hand, int [] deck) {
		int count = 0;
		boolean done = false;
		System.out.println("\n Choose which cards to discard with a X and Hold with an O ");
        System.out.println(" Example: XOXOX would discard the 1st, 3rd and 5th card. ");
		display(hand);
		while (!done) {
			if (in.hasNextLine()) {
				String s = in.nextLine();
				if (s.length() != 0) {
					for (int i = 0; i < s.length(); i++) {
						char ch = s.charAt(i);
						if (ch == 'X' || ch == 'x') hand[count++] = select(deck);
						if (ch == 'O' || ch == 'o') count++;
						if (!(ch == 'X' || ch == 'x' || ch == 'O' || ch == 'o')) count++;
            			done = true;
					}
				}
			}
		}
		return hand;
	}
	
	/** 
	 * Let the user bet a dollar amount of chips on their hand.
	 * This reduces the number of chips of the user until the hand is played.
	 * Don't allow the user to bet more than they have in chips. Reduce the
	 * number of chips that go toward the bet.
	 * @return the number of dollars bet on the hand.
	 */
	public static int bet (Scanner in) {
		int bet = 0;
		boolean done = false;
		
		while( !done ) {
			System.out.println(" You have $" + chips + ".  What is your bet? ");
			if ( in.hasNextInt()) {
				bet = in.nextInt();
				if (bet > chips) {
					System.out.println(" You can not make that bet, Try again ");
					done = false;
				} else {
					done = true;
				}
			} else {
				System.out.println(" Sorry, invalid entry, Try again.");
				done = false;
			}
		}
		chips = chips - bet;
		return bet;
	}
	
	/**
	 * Display a hand of the cards with just a brute force display
	 * @param hand the hand to display
	 */
	public static void display (int [] hand) {
		
      for (int i = 0; i < HAND_SIZE; i++) {	
			if (hand[i] >=  2 && hand[i] <= 14) System.out.print("  Hearts ");
			if (hand[i] >= 15 && hand[i] <= 27) System.out.print("  Spades ");	
			if (hand[i] >= 28 && hand[i] <= 40) System.out.print(" Diamonds");
			if (hand[i] >= 41 && hand[i] <= 53) System.out.print("  Clubs  ");
		}
      System.out.println();
		for (int i = 0; i < HAND_SIZE; i++) {	
            if (hand[i] == 2  || hand[i] == 15 || hand[i] == 28 || hand[i] == 41) System.out.print("  --2--  ");	
			if (hand[i] == 3  || hand[i] == 16 || hand[i] == 29 || hand[i] == 42) System.out.print("  --3--  ");	
			if (hand[i] == 4  || hand[i] == 17 || hand[i] == 30 || hand[i] == 43) System.out.print("  --4--  ");	
			if (hand[i] == 5  || hand[i] == 18 || hand[i] == 31 || hand[i] == 44) System.out.print("  --5--  ");	
			if (hand[i] == 6  || hand[i] == 19 || hand[i] == 32 || hand[i] == 45) System.out.print("  --6--  ");	
			if (hand[i] == 7  || hand[i] == 20 || hand[i] == 33 || hand[i] == 46) System.out.print("  --7--  ");	
			if (hand[i] == 8  || hand[i] == 21 || hand[i] == 34 || hand[i] == 47) System.out.print("  --8--  ");	
			if (hand[i] == 9  || hand[i] == 22 || hand[i] == 35 || hand[i] == 48) System.out.print("  --9--  ");
			if (hand[i] == 10 || hand[i] == 23 || hand[i] == 36 || hand[i] == 49) System.out.print("  -10--  ");		
			if (hand[i] == 11 || hand[i] == 24 || hand[i] == 37 || hand[i] == 50) System.out.print("  --J--  ");
			if (hand[i] == 12 || hand[i] == 25 || hand[i] == 38 || hand[i] == 51) System.out.print("  --Q--  ");
			if (hand[i] == 13 || hand[i] == 26 || hand[i] == 39 || hand[i] == 52) System.out.print("  --K--  "); 
			if (hand[i] == 14 || hand[i] == 27 || hand[i] == 40 || hand[i] == 53) System.out.print("  --A--  ");
		}
		System.out.println();
	}
	
	/**
	 * Select a card from the end of the deck and replace
	 * it with a -1. 
	 * @param deck - the deck of shuffled cards
	 * @return the card selected from the top of the deck
	 */
	public static int select(int [] deck) {
		/* Students complete this code */
	   /* insert code here */
      int card = deck[deck.length-1];
      for (int i = deck.length-1; i >= 1 ; i--) {
         deck[i] = deck[i-1];
       }
       return card;
		/* End of student code */
	}
   
   /**
   * Count the number of cards of each value
   * @param hand  - The hand that was dealt
   * @return the count of the card values in the hand
   */
   public static int [] rank (int [] hand) {
	  // ace has the highest value then, k, q, j, 10, ... 2
	  // since the cards have values from 2 - 54, we need to make them all have
	  // values from 2 - 15, but not to change the hand itself.
	  int [] hold = new int [HAND_SIZE];
	  int [] local = new int [13];
	  
	  
	  /* 
	   * this code converts the 2-53 value into 2-14 values for all cards without destroying
	   * the hand.  It copies the hand into a hold and does the conversion
	   */
	  hold = Arrays.copyOf(hand, HAND_SIZE);
	  for (int i = 0; i < hold.length; i++) {
			/* Students complete this code */
		   /* insert code here */
         if (hand[i]>= 15 && hand[i]<= 27)
            hold[i]=hold[i]-13;
         else if (hand[i]>= 28 && hand[i]<= 40)
            hold[i]=hold[i]-26;
         else if (hand[i]>= 41 && hand[i]<= 53)
            hold[i]=hold[i]-39;
			/* End of student code */
	  }

	  /* 
	   * This code counts the cards as pairs, singles three of a kind, four of a kind, etc.
	   * As an example if hold[0] is a 2, then local[2] would get 1 added to it
	   * If hold [1] was also a 2, then local[2] would have another 1 added to it
	   * This would show that there is a pair of 2's in the hand
	   */
      for (int i = 0; i < hold.length; i++) local[hold[i] -2]++;  
      return local;
   }
   
   
   /**
    * Determine who won the hand.  The user or the computer.
    * @param user  - the ranking of the user hand
    * @param computer - the ranking of the computer hand
    * @return - the user or the computer
    */
   public static String score(int [] user, int [] computer) {
	   
	   String userWins = "user";
	   String computerWins = "computer";
	   String winner = "";
	   
		/* Students complete this code */
	   /* insert code here */
      int userscore = 0;
      int computerscore = 0;
      int userhigh = 0;
      int computerhigh = 0;
      boolean userpair = false;
      boolean userthree = false;
      boolean computerpair = false;
      boolean computerthree = false;
      // 4 of kind get 7 points
      // 3 of kind get 3 points
      // pair get 1 point
      // else compare the highest card
      for (int i = 0; i < user.length; i++) {
         if (user[i] == 4) {
            userscore += 7;
         }
         if (user[i] == 3) {
            userthree = true;
            userscore += 3;
         }
         if (user[i] == 2) {
            userpair = true;
         }
         if (computer[i] == 4) {
            computerscore += 7;
         }
         if (computer[i] == 3) {
            computerthree = true;
            computerscore += 3;
         }
         if (computer[i] == 2) {
            computerpair = true;
         }
      }
      if(userpair && userthree) {
         userscore+=5;
      }
      else if(userpair) {
         for(int i=0; i<user.length; i++) {
            if(user[i]==2)
               userscore++;
         }
      }
      if(computerpair && computerthree) {
         computerscore+=5;
      }
      else if(computerpair) {
         for(int i=0; i<user.length; i++) {
            if(computer[i]==2)
               computerscore++;
         }
      }
      if (userscore > computerscore)
         winner = userWins;
      if (userscore < computerscore)
         winner = computerWins;
      if (userscore == 0 && computerscore == 0) {
            for (int i = 0; i < user.length; i++) {
               if (user[i]>userhigh)
                  userhigh = user[i];
               if (computer[i]>computerhigh)
                  computerhigh = computer[i];
            }
         }
      if (userhigh > computerhigh)
         winner = userWins;
      else if (userhigh < computerhigh)
         winner = computerWins;
      if (computerscore == userscore) {
         winner = userWins;
      }
      if (computerhigh == userhigh) {
         winner = userWins;
      }
		/* End of student code */

      return winner; 
   }
 }