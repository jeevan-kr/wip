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
public class RouletteGame {
    Wheel wheel;
    Table table;
    Player player;
    
    /**
     * Constructs a new RouletteGame given Wheel and a Table.
     * 
     * @param wheel – The wheel which produces random events
     * @param table  – The table which holds bets to be resolved
     */
    public RouletteGame(Wheel wheel, Table table){
        this.wheel = wheel;
        this.table = table;
    }
    
    /**
     * Executes a single cycle of play with a single Player. It will call the 
     * Player to place bets and call the Wheel to get the winning bets. It will
     * notify the player of the winning and the loosing Bets.
     * @param player 
     */
    public void cycle(Player player) {
        player.placeBets();
        Bin winningBin = wheel.next();
        
    }
    
    
}
