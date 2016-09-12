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

/**
 *
 * @author Jeevan
 */
public class Martingale extends Player {
    int lossCount;
    int betMultiple;
    int baseBetAmt;
    public Martingale(Table table) {
        this.table = table;
        betMultiple = 1;
    }
    @Override
    public void placeBets() throws InvalidBetException {
        Outcome betOutcome = table.wheel.getOutcome("Black");
        table.placeBet(new Bet(betMultiple, black));
    }

    @Override
    public void win(Bet bet) {
        super.win(bet);
        lossCount = 0;
        betMultiple = 1;
    }

    @Override
    public void lose(Bet bet) {
        super.lose(bet);
        lossCount++;
        betMultiple = 2>>lossCount; 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStake(int stake) {
        this.stake = stake;
    }

    @Override
    public void setRounds(int rounds) {
        this.roundsToGo = rounds;
    }

    @Override
    public boolean playing() {
        return isPlaying;
    }
    
}
