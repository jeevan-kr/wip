/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.casino.roulette;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author jeevan
 */
public class Bin {
    Set<Outcome> outcomes; //winning outcomes for the given bin
    /**
     * An empty constructor for Bin.
     * 
     */
    public Bin() {
        
    }
    
    /**
     * Creates an empty Bin using the this() statement to invoke the default 
     * constructor. It then loads that collection using elements of the given 
     * array.
     * 
     * @param outcomesInput (Outcome[] array) – A primitive array of outcomes.
     */
    public Bin(Outcome[] outcomesInput) {
        initOutcomes();
        
        for(Outcome element : outcomesInput)
            this.outcomes.add(element);
    }
    
    /**
     * Creates an empty Bin using the this() statement to invoke the default 
     * constructor. It then loads that collection using an iterator over the 
     * given Collection. This relies on the fact that all classes that 
     * implement Collection will provide the iterator(); the constructor can 
     * convert the elements of the input collection to a proper Set.
     * 
     * @param outcomesInput (Collection) – A collection of outcomes.
     */
    public Bin(Collection<Outcome> outcomesInput) {
        initOutcomes();
        
        for(Outcome element : outcomesInput)
            this.outcomes.add(element);
    }
    
    private void initOutcomes() {
        if(outcomes == null) 
            outcomes = new HashSet<Outcome>();
    }
    /**
     * Adds an Outcome to this Bin. This method simply delegates the real work 
     * to the underlying collection.
     * 
     * @param outcome (Outcome) – An outcome to add to this Bin
     */
    public void add(Outcome outcome) {
        initOutcomes();
        this.outcomes.add(outcome);
    }
    
    /**
     * An easy-to-read representation of the list of Outcomes in this Bin.
     * @return String of the form [outcome, outcome, ...].
     */
    @Override
    public String toString() {
        StringBuffer retVal = new StringBuffer("[");
        for(Outcome element: this.outcomes)
            retVal.append(element.toString())
                    .append(" ");
        
        retVal.append("]");
        return retVal.toString();
    }
}
