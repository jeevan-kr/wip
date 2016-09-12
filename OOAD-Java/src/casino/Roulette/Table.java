/*
 * Copyright 2015 v-fnjeev.
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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author v-fnjeev
 */
public class Table {
    
    int limit;
    List<Bet> bets;
    Wheel wheel;
    public Table(Wheel wheel) {
        bets = new LinkedList<Bet>();
        this.wheel = wheel;
    }
    
    public boolean isValid(Bet testBet) {
        boolean retVal = true;
        int totalBetAmount = 0;
        totalBetAmount = sumBets();
        totalBetAmount += testBet.amountBet;
        //System.out.println("Bet Amount " + totalBetAmount + " " + testBet.amountBet);
        if(totalBetAmount > limit)
            retVal = false;
        return retVal;
    }
    
    public void placeBet(Bet bet) throws InvalidBetException {
        if(!isValid(bet))
            throw new InvalidBetException();
        else 
            bets.add(bet);
    }
    
    private int sumBets() {
        int totalBetAmount = 0;
        for(Bet bet : bets) {
            totalBetAmount += bet.amountBet;
        }
        return totalBetAmount;
    }
    
    public ListIterator iterator() {
        return bets.listIterator();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Bet bet : bets) {
            sb.append(bet.toString());
        }
        return sb.toString();
    }

    
}
