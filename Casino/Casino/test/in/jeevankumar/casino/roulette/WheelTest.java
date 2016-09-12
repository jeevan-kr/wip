/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.casino.roulette;

import java.util.Random;
import java.util.Set;
import junit.framework.TestCase;

/**
 *
 * @author jeevan
 */
public class WheelTest extends TestCase{
    Wheel wl;
    Random rng;
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
        rng = new NonRandom(0);
        wl = new Wheel(rng);
    }
    
    public void testAdd() {
        
        Outcome five = new Outcome( "00-0-1-2-3", 6 );
        Outcome red = new Outcome( "red", 2 );
        Outcome black = new Outcome( "black", 2 );
        
        Outcome[] ocZero = {
            new Outcome("0", 35 ), five, red };
        Outcome[] ocZeroZero = {
            new Outcome("00", 35 ), five, black };
        
        for(Outcome element: ocZero) {
            wl.addOutcome(0, element);
        }
        
        for(Outcome element: ocZeroZero) {
            wl.addOutcome(37, element);
        }
        
        rng.setSeed(0);
        Bin zeroBin = wl.next();
        
        Set outcomes = zeroBin.outcomes;
        for(Outcome element: ocZero) {
            assertTrue(outcomes.contains(element));
        }
        
        rng.setSeed(37);
        Bin zeroZeroBin = wl.next();
        
        outcomes = zeroZeroBin.outcomes;
        for(Outcome element: ocZeroZero) {
            assertTrue(outcomes.contains(element));
        }
        
    }
}
