/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.blackjack;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class BlackJack {

    ConsoleIO console = new ConsoleIO();
    Random generator = new Random();
    int cardID = 0;
    int compCardID = 0;
    String cardRank = "";
    int cardValue = 0;
    int humanTotal = 0;
    int compTotal = 0;
    int compPlays = 1;
    double winnings = 100;
    double bet = 0;
    boolean aceHigh;
    boolean hit;
    boolean drawNew = true;
    boolean busted = false;
    boolean blackJack = false;
    boolean compHit = true;
    boolean play = true;
    boolean humanPlayer = true;
    boolean win = false;

    Map<Integer, BlackJack> humanCards = new HashMap();
    Map<Integer, BlackJack> compCards = new HashMap();

    public void run() {

        console.readString("Welcome to Daniel's BlackJack\n");

        while (play == true) {

            resetGame(cardID, humanTotal, compTotal, drawNew, compHit, busted, humanCards, compCards);

            bet = inputBet();

            aceHigh = acesHigh();

            addStartingHumanCards(aceHigh);

            addCompCards(aceHigh);

            firstRound();

            dealingLoop(drawNew);

            if (busted == false && blackJack == false) {

                while (compHit == true && compTotal < 17) {

                    compTotal = calculateCompTotal();

                    determineCompHit();

                    compBlackJackBust();

                }
            }

            showResult();

            askPlayAgain();
        }
        goodByeMessage();
    }

    public int generateCard() {

        int card = generator.nextInt(13) + 1;

        return card;

    }

    public void addStartingHumanCards(boolean acesHigh) {

        boolean aceHigh = acesHigh;

        for (int i = 0; i < 2; i++) {

            this.cardID++;

            BlackJack bjObj = new BlackJack();
            int humanCard = generateCard();
            String cardR = checkCardRank(humanCard);
            int cardV = modifyCardValue(humanCard, aceHigh);

            bjObj.setCardValue(cardV);
            bjObj.setCardRank(cardR);

            humanCards.put(cardID, bjObj);

        }

    }

    public void addCompCards(boolean acesHigh) {

        boolean aceHigh = acesHigh;

        this.compCardID++;
        BlackJack bjObj = new BlackJack();
        int compCard = generateCard();
        String compR = checkCardRank(compCard);
        int compV = modifyCardValue(compCard, aceHigh);

        bjObj.setCardValue(compV);
        bjObj.setCardRank(compR);

        compCards.put(compCardID, bjObj);

    }

    public void setCardRank(String cardRank) {
        this.cardRank = cardRank;
    }

    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    public void firstRound() {

        showHumanCards();
        blackJack = checkBlackJack();
        if (blackJack == true) {
            drawNew = false;
            win = true;
        } else {

            showCompCard();

        }
    }

    public void showHumanCards() {

        Set<Integer> setOfCards = humanCards.keySet();
        int total = 0;
        int i = 0;
        System.out.print("\nYou received a ");
        for (Integer cardNumber : setOfCards) {

            BlackJack bjObj = humanCards.get(cardNumber);
            String cardRank = bjObj.getCardRank();
            int cardValue = bjObj.getCardValue();
            if (i == 1) {
                console.readString(cardRank + ".");
            } else {
                System.out.print(cardRank + " and a ");
                i++;
            }
            total += cardValue;
        }
        console.readString("Your total is " + total + ".\n");
    }

    public void showCompCard() {

        Set<Integer> setOfCards = compCards.keySet();

        System.out.print("The dealer is showing a ");
        for (Integer cardNumber : setOfCards) {

            BlackJack bjObj = compCards.get(cardNumber);
            String cardRank = bjObj.getCardRank();
            System.out.print(cardRank + ". ");
        }

    }

    public void hitHumanCard(boolean acesHigh) {

        boolean aceHigh = acesHigh;

        BlackJack bjObj = new BlackJack();
        int humanCard = generateCard();
        String cardR = checkCardRank(humanCard);
        int cardV = modifyCardValue(humanCard, aceHigh);

        bjObj.setCardValue(cardV);
        bjObj.setCardRank(cardR);

        this.cardID++;

        console.readString("You received a " + cardR);
        humanCards.put(cardID, bjObj);

    }

    public void showHumanHitTotal() {

        Set<Integer> setOfCards = humanCards.keySet();
        int total = 0;
        for (Integer cardNumber : setOfCards) {

            BlackJack bjObj = humanCards.get(cardNumber);
            int cardValue = bjObj.getCardValue();

            total += cardValue;
        }
        console.readString("Your total is now " + total + "\n");

    }

    public void compDraws(boolean acesHigh) {

        boolean aceHigh = acesHigh;

        BlackJack bjObj = new BlackJack();
        int compCard = generateCard();
        cardRank = checkCardRank(compCard);
        compCard = modifyCardValue(compCard, aceHigh);

        bjObj.setCardValue(compCard);
        bjObj.setCardRank(cardRank);
        if (compPlays == 1) {
            console.readString("\nThe computer revealed a " + cardRank + ".");
            this.compCardID++;
            compPlays++;
        } else {
            console.readString("\nThe computer hit and received a " + cardRank + ".");
            this.compCardID++;
        }
        compCards.put(compCardID, bjObj);

    }

    public void dealingLoop(boolean keepHitting) {

        while (drawNew == true) {
            hit = hitOrStay();

            if (hit == true) {

                console.readString("\nYou were dealt another card!");
                hitHumanCard(aceHigh);
                showHumanHitTotal();
                busted = checkBust();
                blackJack = checkBlackJack();

                if (busted == true) {

                    console.readString("Bust!");
                    break;

                } else if (blackJack == true) {

                    win = true;
                    break;
                }

            } else {

                drawNew = false;

            }

        }

    }

    public void showCompCardTotal() {

        int total = 0;

        total = calculateCompTotal();

        console.readString("\nThe dealer's total is " + total + ".");

    }

    public void determineCompHit() {
        if (compTotal >= 17 && busted == false) {
            console.readString("\nThe dealer stays.");
            showCompCardTotal();
            humanTotal = showHumanTotal();
        } else {
            compDraws(aceHigh);
            showCompCardTotal();
            blackJack = checkBlackJackComp();
            busted = checkBustComp();
        }

    }

    public void compBlackJackBust() {
        if (blackJack == true) {
            win = false;
            compHit = false;

        } else if (busted == true) {
            console.readString("\nDealer busted!");
            win = true;
            compHit = false;
        }

    }

    public void showResult() {
        if (humanTotal > compTotal || win == true) {
            console.readString("You win!");
            if (blackJack == true) {
                winnings = winnings + (bet * 1.5);
            } else {
                winnings = winnings + bet;
            }
        } else if (humanTotal == compTotal && busted == false) {
            console.readString("It's a draw!");
        } else {
            console.readString("You lose!");
            winnings = winnings - bet;
        }
    }

    public void askPlayAgain() {
        if (winnings <= 0) {
            console.readString("You're broke!");
            play = false;
        } else {
            play = console.yesCheck("\nWould you like to play again? [yes/no]\n>",
                    "\nThat is not an option!\n");
        }

    }

    public void goodByeMessage() {
        if (winnings <= 0) {
            console.readString("Don't let the door hit you on your way out!");
        } else {
            console.readString("Thanks for playing! You left with $" + winnings);
        }
    }

    public void resetGame(int cardKey, int hCardTotal, int cCardTotal, boolean hit, boolean cHit, boolean bust, Map humanC, Map compC) {

        this.cardID = 0;
        this.humanTotal = 0;
        this.compTotal = 0;
        this.drawNew = true;
        this.compHit = true;
        this.busted = false;
        this.humanCards.clear();
        this.compCards.clear();

    }

    public int showHumanTotal() {

        Set<Integer> setOfCards = humanCards.keySet();
        int total = 0;
        for (Integer cardNumber : setOfCards) {

            BlackJack bjObj = humanCards.get(cardNumber);
            int cardValue = bjObj.getCardValue();
            total += cardValue;
        }
        console.readString("Your total is " + total + ".\n");
        return total;
    }

    public int calculateCompTotal() {

        Set<Integer> setOfCards = compCards.keySet();
        int total = 0;

        for (Integer cardNumber : setOfCards) {

            BlackJack bjObj = compCards.get(cardNumber);
            int cardValue = bjObj.getCardValue();
            total += cardValue;
        }

        return total;
    }

    public int getCardValue() {
        return cardValue;
    }

    public int modifyCardValue(int cardValue, boolean aceHigh) {

        switch (cardValue) {

            case 1:
                if (aceHigh == false) {
                    cardValue = 1;
                } else {
                    cardValue = 11;
                }
                break;
            case 11:
            case 12:
            case 13:
                cardValue = 10;
                break;

        }
        return cardValue;
    }

    public double inputBet() {

        console.readString("You have $" + winnings);
        double playerBet = console.checkMinMaxDouble("How much would you like to bet?\n>$", 1, 100, "You have to bet at least $1!",
                "You can't bet more than you have!", "You have to bet money!");
        return playerBet;
    }

    public String checkCardRank(int cardValue) {

        String cardFace;
        cardFace = getFaceCardValue(cardValue);
        return cardFace;
    }

    public String getFaceCardValue(int cardValue) {

        String cardFace;
        switch (cardValue) {

            case 1:

                cardFace = "Ace";
                break;

            case 11:

                cardFace = "Jack";
                break;

            case 12:

                cardFace = "Queen";
                break;

            case 13:

                cardFace = "King";
                break;

            default:

                cardFace = Integer.toString(cardValue);
                break;

        }

        return cardFace;

    }

    public String getCardRank() {
        return cardRank;
    }

    public boolean acesHigh() {

        boolean valid = false;
        boolean aceIsHigh = true;

        while (!valid) {
            console.readString("\nWould you like to play Aces high or low?");
            String playerChoice = console.getString(">");
            if (playerChoice.equalsIgnoreCase("high")) {
                aceIsHigh = true;
                valid = true;
            } else if (playerChoice.equalsIgnoreCase("low")) {

                aceIsHigh = false;
                valid = true;

            } else {
                console.readString("That is not an option!");
                valid = false;
            }
        }
        return aceIsHigh;
    }

    public boolean hitOrStay() {

        boolean valid = false;
        boolean hit = true;

        while (!valid) {
            console.readString("Will you hit or stay?");
            String playerChoice = console.getString(">");

            if (playerChoice.equalsIgnoreCase("hit")) {
                hit = true;
                valid = true;
            } else if (playerChoice.equalsIgnoreCase("stay")) {

                hit = false;
                valid = true;

            } else {
                console.readString("That is not an option!");
                valid = false;
            }
        }
        return hit;

    }

    public boolean checkBust() {

        boolean bust = false;

        Set<Integer> setOfCards = humanCards.keySet();
        int total = 0;
        for (Integer cardNumber : setOfCards) {

            BlackJack bjObj = humanCards.get(cardNumber);
            int cardValue = bjObj.getCardValue();

            total += cardValue;
        }

        if (total > 21) {

            bust = true;
        }

        return bust;
    }

    public boolean checkBustComp() {

        boolean bust = false;

        Set<Integer> setOfCards = compCards.keySet();
        int total = 0;
        for (Integer cardNumber : setOfCards) {

            BlackJack bjObj = compCards.get(cardNumber);
            int cardValue = bjObj.getCardValue();

            total += cardValue;
        }

        if (total > 21) {

            bust = true;
        }

        return bust;
    }

    public boolean checkBlackJack() {
        boolean jack = false;
        Set<Integer> setOfCards = humanCards.keySet();
        int total = 0;
        for (Integer cardNumber : setOfCards) {

            BlackJack bjObj = humanCards.get(cardNumber);
            int cardValue = bjObj.getCardValue();

            total += cardValue;
        }

        if (total == 21) {
            console.readString("BlackJack!");
            jack = true;
        }

        return jack;
    }

    public boolean checkBlackJackComp() {
        boolean jack = false;
        Set<Integer> setOfCards = compCards.keySet();
        int total = 0;
        for (Integer cardNumber : setOfCards) {

            BlackJack bjObj = compCards.get(cardNumber);
            int cardValue = bjObj.getCardValue();

            total += cardValue;
        }

        if (total == 21) {
            console.readString("BlackJack!");
            jack = true;
        }

        return jack;
    }

}
