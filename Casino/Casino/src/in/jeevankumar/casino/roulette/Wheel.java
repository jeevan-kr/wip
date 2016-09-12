/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.casino.roulette;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author jeevan
 */
public class Wheel {
    List<Bin> bins; //contains individual bin instances
    Random rng; //reference to a random number generator
    Map<String, Outcome> allOutcomes;
    /**
     * Creates a new Wheel with 38 bins and also sets the instance's random 
     * number generator reference to the rng parameter.
     * 
     * @param rng (java.util.Random) – A “random” number generator. 
     * For testing, this may be a non-random number generator.
     */
    public Wheel(Random rng) {
        int limit = 38;
        this.rng = rng;
        
        this.bins = new ArrayList<>(limit);
        this.allOutcomes = new HashMap<>();
        for(int i = 0; i < limit; i++) {
            bins.add(new Bin());
        }
    }

    
    /**
     * Adds the given Outcome to the Bin with the given number.
     * 
     * @param bin (int) – bin number, in the range zero to 37 inclusive.
     * @param outcome (Outcome) – The Outcome to add to this Bin
     */
    public void addOutcome(int bin, Outcome outcome) {
        bins.get(bin).add(outcome);
        allOutcomes.put(outcome.name, outcome);
    }
    
    /**
     * Generates a random number between 0 and 37, and returns corresponding 
     * the randomly selected bin. Note that there bins ranging from 
     * 00, 0...36. 
     * 
     * Returns: A Bin selected at random from the Wheel.
     * 
     * @return Bin 
     */
    public Bin next() {
        int rand = rng.nextInt(38);
        return this.bins.get(rand);
        
    }
    
    /**
     * Returns the given Bin from the internal collection. Note that 37 is the 
     * bin number designated to bin '00'. All other index numbers are designated
     * to obvious bin i.e., index 2 corresponds to bin 2.
     * Returns: The requested Bin
     * @param bin (int) - bin number in the range 0 to 37.
     * @return 
     */
    public Bin get(int bin) {
        return bins.get(bin);
    }
    
    public Outcome getOutcome(String name) {
        return allOutcomes.get(name);
    }
}
