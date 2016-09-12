/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.casino.roulette;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Table contains all the Bet s created by the Player. A table also has a 
 * betting limit, and the sum of all of a player’s bets must be less than or 
 * equal to this limit. We assume a single Player in the simulation.
 * 
 * @author jeevan
 */
public class Table {
    List<Bet> bets;
    int limit;
    public Table(int limit) {
        bets = new LinkedList<>();
        this.limit = limit;
    }
    /**
     * Validates this bet. If the sum of all bets is less than or equal to the 
     * table limit, then the bet is valid, return true. Otherwise, return false.
     * 
     * @param bet - boolean
     * @return bet (Bet) – A Bet instance to be validated.
     */
    public boolean isValid(Bet bet) {
        ListIterator lIter = bets.listIterator();
        int sum = 0;
        while(lIter.hasNext()) {
            Bet temp = (Bet) lIter.next();
            sum += temp.betAmount;
        }
        
        return (sum <= limit);
    }
    /**
     * Adds this bet to the list of working bets. If the sum of all bets is 
     * greater than the table limit, then an exception should be thrown. This 
     * is a rare circumstance, and indicates a bug in the Player more than 
     * anything else.
     * 
     * Raises: InvalidBetException
     * @param bet (Bet) – A Bet instance to be validated.
     */
    public void placeBet(Bet bet) {
        bets.add(bet);
    }
    
    /**
     * Returns a ListIterator over the list of bets. This gives us the freedom 
     * to change the representation from LinkedList to any other Collection 
     * with no impact to other parts of the application.
     * 
     * @return iterator over all bets
     */
    public ListIterator iterator() {
        return bets.listIterator();
    }
}
