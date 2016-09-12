/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.casino.roulette;

//import in.jeevankumar.casino.Outcome;
import junit.framework.TestCase;

/**
 * 
 * @author jeevan
 */
public class OutcomeTest extends TestCase {
   
   /**
    * Test equality of the Outcome objects. Two Outcome objects are 
    * considered equal if their names match.
    * 
    */
   public void testEquals() {
       Outcome out1 = new Outcome("Red", 35);
       Outcome out2 = new Outcome("Red", 36);
       Outcome out3 = new Outcome("Black", 35);
       
       assertTrue(out1.equals(out2));
       assertTrue(out2.equals(out1));
       assertEquals(out1.hashCode(), out2.hashCode());
       
       assertFalse(out2.equals(out3));
       assertFalse(out3.equals(out2));
       assertTrue(out2.hashCode()!= out3.hashCode());
       
       assertFalse(out3.equals(out1));
       assertFalse(out1.equals(out3));
       assertTrue(out1.hashCode()!= out3.hashCode());
       
   } 
   
   /**
    * Test the winAmount function of the Outcome object.
    */
   public void testWinAmount() {
       Outcome out1 = new Outcome("Red", 35);
       Outcome out2 = new Outcome("Red", 36);
       assertEquals(out1.winAmount(5), 5*35);
       assertEquals(out2.winAmount(16), 16*36);
   }
   
}
