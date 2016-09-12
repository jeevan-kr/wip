/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.casino.roulette;

import java.util.Random;

/**
 * This is used to test the Wheel class. This class extends java.util.Random. 
 * The output of 'nextInt()' can be controlled by setting seed.
 * 
 * @author jeevan
 */
public class NonRandom extends Random {
    int value;
    /**
     * This is an empty constructor for non-random.
     */
    public NonRandom(int seed) {
        super(seed);
    }
    
    /**
     * Saves the value as the next value to return.
     * 
     * @param seed (long) - the value to return next
     */
    @Override
    public void setSeed(long seed) {
        this.setSeed((int)seed);
    }
    
    /**
     * Saves the value as the next value to return.
     * 
     * @param seed (int) - the value to return next
     */
    public void setSeed(int seed) {
        if( seed < 0 || seed > 37)
            throw new IllegalArgumentException(
                    seed + ": Value must be between 0 and 37, inclusive");
        this.value = seed;
    }
    /**
     * This function returns the value set by 'setSeed()'.
     * 
     * @param bound (int) - not used
     * @return (int) the seed value
     */
    @Override
    public int nextInt(int bound){
        return value;
    }
    
}
