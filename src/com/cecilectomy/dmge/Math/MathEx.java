package com.cecilectomy.dmge.Math;

public class MathEx {
	
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
    
    public static float clamp(float x, float a, float b) {
        return x < a ? a : (x > b ? b : x);
    }    
}
