/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package GamePackage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class GameBotApp {

    public static void main(String[] args) {

        ConsoleIO console = new ConsoleIO();
        Game ls = new LuckySevens();
        Game ic = new InterestCalculator();
        Game fz = new Factorizer();
        Game rps = new RockPaperScissors4();

        List<Game> games = new ArrayList();

        games.add(fz);
        games.add(ic);
        games.add(ls);
        games.add(rps);

        boolean stillPlaying = true;

        while (stillPlaying) {

            console.readString("\nWelcome to GameBot!");
            for (Game myGame : games) {
                console.readString("Game name: " + myGame.getName());
            }

            String input = console.getString("\nPlease enter a game to play: ");

            for (Game myGame : games) {

                if (input.equals(myGame.getName())) {
                    console.readString("");
                    myGame.run();
                    break;
                }
            }

            if ("exit".equals(input)) {
                stillPlaying = false;
            }
        }

    }
}
