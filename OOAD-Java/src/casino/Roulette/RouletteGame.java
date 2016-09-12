/*
 * Copyright 2015 Jeevan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package casino.Roulette;

import java.util.ListIterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a demo program. Creates a Wheel, a stub Passenger57, and Table. It
 * creates a Game object and cycles it a few times. 
 * @author Jeevan
 */
public class RouletteGame {
    Random rand;
    Wheel rouletteWheel;
    Table rouletteTable;
    public static void main(String args[]) {
        RouletteGame rGame = new RouletteGame();
        rGame.run();
    }
    
    /**
     * Simply runs the Roulette Game. 
     */
    public void run() {
        //Create Table and the wheel
        setUp();
        
        //Create the player
        Player p = new Passenger57(rouletteTable);
        p.setStake(3);
        int run = 0;
        while(p.playing()) {
            try {
                //Call player to place bets
                p.placeBets();
                ++run;
                System.out.println( run + ": " + rouletteTable );
                
                //spin wheel
                Bin winningBin = rouletteWheel.next();
                
                //notify player of win/loss
                ListIterator lIter = rouletteTable.iterator();
                while(lIter.hasNext()) {
                    Bet bet = (Bet) lIter.next();
                    
                    lIter.remove();
                    
                    if(winningBin.outcomes.contains(bet.outcome)) {
                        p.win(bet);
                    } else {
                        p.lose(bet);
                    }
                    
                }
            } catch (InvalidBetException ex) {
                Logger.getLogger(RouletteGame.class.getName()).log(Level.SEVERE, null, ex);
            }

        
        }
        
        
    }
    
    private void setUp() {
        rand = new NonRandomForTest(1);
        rouletteWheel = new Wheel(rand);
        BinBuilder.getInstance().buildBins(rouletteWheel);
        rouletteTable = new Table(rouletteWheel);
        rouletteTable.limit = 1;
    }
    
    private void cycle(Player player) {
        
        
    }
}
