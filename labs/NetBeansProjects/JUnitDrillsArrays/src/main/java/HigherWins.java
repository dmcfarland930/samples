/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

/**
 *
 * @author apprentice
 */
public class HigherWins {

    public int[] higherWins(int[] values) {

        int[] result = new int[values.length];

        if (values[0] > values[values.length - 1]) {

            for (int i = 0; i < values.length; i++) {
                result[i] = values[0];
            }

        } else {
            for (int i = 0; i < values.length; i++) {
                result[i] = values[values.length - 1];
            }
        }

        return result;

    }
}
