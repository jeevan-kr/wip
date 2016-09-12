/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.casino.roulette;

import com.sun.istack.internal.logging.Logger;
import java.util.Random;
import java.util.logging.Level;

/**
 * PlayerBlack only bets on black
 * @author jeevan
 */
public class PlayerBlack implements Player {
    Outcome black;
    Table table;
    
    public PlayerBlack(Table table) {
        this.table = table;
        black = new Outcome("black", 0);
    }
    /**
     * Places bets on the table. 
     * 
     */
    public void placeBets() {
        Random rand = new Random();
        
        for (int i = 0; i < 10; i++) {
            int betAmount = rand.nextInt() * 10 + 100;
            String message = "Betting " + betAmount;
            table.placeBet(new Bet(100 + betAmount, black));
            
            Logger logger = Logger.getLogger(PlayerBlack.class);
            logger.log(Level.ALL, message);
        
            System.out.println(message);
        }
    }
    
    /**
     * The CasinoGame calls this function is called with the winning bet. 
     * 
     * @param bet  – The bet which won.
     */
    public void win(Bet bet) {
        String message = this.toString() + " won " + bet.winAmount();
        Logger logger = Logger.getLogger(PlayerBlack.class);
        logger.log(Level.ALL, message);
        
        System.out.println(message);
    }
    
    
    /**
     * The CasinoGame calls this function is called with the loosing bet.
     * 
     * @param bet  – The bet which won.
     */
    public void lose(Bet bet) {
        String message = this.toString() + " lost " + bet.winAmount();
        Logger logger = Logger.getLogger(PlayerBlack.class);
        logger.log(Level.ALL, message);
        
        System.out.println(message);
    }
    
}
