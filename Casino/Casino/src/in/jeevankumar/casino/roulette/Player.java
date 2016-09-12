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
public interface Player {
    /**
     * Places bets on the table. 
     * 
     */
    public void placeBets();
    
    /**
     * The CasinoGame calls this function is called with the winning bet. 
     * 
     * @param bet  – The bet which won.
     */
    public void win(Bet bet);
    
    /**
     * The CasinoGame calls this function is called with the loosing bet.
     * 
     * @param bet  – The bet which won.
     */
    public void lose(Bet bet);
    
    
}
