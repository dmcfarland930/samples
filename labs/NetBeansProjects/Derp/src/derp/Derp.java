/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package derp;

/**
 *
 * @author apprentice
 */
public class Derp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int n = 4;
        if ((n & (n - 1)) == 0) {

            System.out.println("true");

        }

        boolean isFactor = false;

        while (n >= 2) {

            if (n % 2 == 0) {
                isFactor = true;
            } else {
                isFactor = false;
                break;
            }

            n = n / 2;

        }
        
        
        
        if(isFactor){
            System.out.println("True");
            
        }

    }

}
