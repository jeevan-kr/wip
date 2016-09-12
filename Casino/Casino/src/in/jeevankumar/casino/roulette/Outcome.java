/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.casino.roulette;

import java.text.MessageFormat;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author jeevan
 */
public class Outcome {
    String name;
    int odds;
    
    /**
     * Sets the instance name and odds from the parameter nameInput and 
     * oddsInput.
     * @param nameInput (String) – The name of this outcome
     * @param oddsInput  (int) – The payout odds of this outcome.
     */
    public Outcome(String nameInput, int oddsInput) {
        this.name = nameInput;
        this.odds = oddsInput;
    }
    /**
     * Multiply this Outcome's odds with the given amount. The product is 
     * returned.
     * @param bet (int) – amount being bet
     * @return amount won based on the Outcome's odds and bet amount. 
     */
    public int winAmount(int bet) {
        int retVal = bet * odds;
        
        return retVal;
    }
    
    /**
     * Compare the name attributes of this and other. 
     * 
     * @param other (Outcome) - Another outcome to compare against
     * @return true if this name matches the other name.
     */
    public boolean equals(Outcome other) {
        return (this.name.equals(other.name));
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(7,13)
                .append(name)
                .toHashCode();
    }
    
    /**
     * Easy-to-read representation of this Outcome.
     * @return String of the form name (odds:1)
     */
    public String toString() {
        Object[] values= { name, new Integer(odds) };
        String msgTempl= "{0} ({1}:1)";
        return MessageFormat.format( msgTempl, values );
    }
    
}
