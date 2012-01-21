package deadmarslib.Utility;

/**
 * DeadMarsLib MathUtility Class
 *
 * @author Cecil
 */
public class MathUtility {
    
    /**
     * Determines whether or not a real number is prime.
     * 
     * @param num Number to check for primality.
     * @return Whether or not number is prime.
     */
    public static boolean isPrime(int num) {
        if (num >= 2) {
            for (int j = 2; j <= Math.sqrt((double) num); j++) {
                if (num % j == 0) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }
    
    /**
     * Clamps a specified number to a specified range.
     * 
     * @param x Number to clamp.
     * @param a Beginning of range to clamp to.
     * @param b End of range to clamp to.
     * @return Number after clamp.
     */
    public static float clamp(float x, float a, float b) {
        return x < a ? a : (x > b ? b : x);
    }    
}
