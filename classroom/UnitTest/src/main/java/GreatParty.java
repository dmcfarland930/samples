/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

/**
 *
 * @author apprentice
 */
public class GreatParty {

    public boolean GreatParty(int cigars, boolean isWeekend) {

        if (isWeekend) {

            if (cigars > 40) {
                return true;
            } else {
                return false;
            }

        } else if (cigars >= 40 && cigars <= 60) {
            return true;
        } else {
            return false;
        }

    }

}
