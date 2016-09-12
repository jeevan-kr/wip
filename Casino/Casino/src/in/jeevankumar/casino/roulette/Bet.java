/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.casino.roulette;

/**
 *
 * @author jeevan
 */
public class Bet {
    int betAmount;
    Outcome outcome;
    
    public Bet(int amount, Outcome outcome) {
        this.betAmount = amount;
        this.outcome = outcome;
    }
    
    public int winAmount() {
        return outcome.winAmount(betAmount);
    }
    
    public int loseAmount() {
        return betAmount;
    }
    
    public String toString() {
        return betAmount + " on " + outcome;
    }
}
