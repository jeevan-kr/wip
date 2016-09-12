/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.casino.roulette;

import java.util.Random;
import junit.framework.TestCase;

/**
 *
 * @author jeevan
 */
public class NonRandomTest extends TestCase {
    NonRandom nr;
    Random rng;
        
    @Override
    public void setUp() throws Exception {
        super.setUp();
        nr = new NonRandom(0);
        rng = new Random();
        
    }
    public void testNextInt() {
        int i = 10;
        while(i-- > 0) {
            int rand = rng.nextInt(38);
            nr.setSeed(rand);
            
            assertEquals(nr.nextInt(38), rand);
        }
    }
    
    public void testSetSeed() {
        //IllegalArgumentException must be thrown for negative number        
        try {
            nr.setSeed(-rng.nextInt());
        } catch(Exception e) {
            assertTrue(e instanceof java.lang.IllegalArgumentException);
        }
        
        //IllegalArgumentException must be thrown for number greater than 37
        try {
            nr.setSeed(rng.nextInt() + 1 + 37);
        } catch(Exception e) {
            assertTrue(e instanceof java.lang.IllegalArgumentException);
        }
    }
    
}
