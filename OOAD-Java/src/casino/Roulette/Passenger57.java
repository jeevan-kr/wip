package casino.Roulette;

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

/**
 *
 * @author Jeevan
 */
public class Passenger57 extends Player{
    Table table;
    boolean isPlaying;
    int bettingAmount;
    public Passenger57(Table table) {
        this.table = table;
        isPlaying = true;
        bettingAmount = 1;
    }
    
    @Override
    public void placeBets() throws InvalidBetException {
        stake -= bettingAmount;
        Outcome black = table.wheel.getOutcome("Black");
        table.placeBet(new Bet(bettingAmount,black));
    }
    
    @Override
    public void win(Bet bet) {
        stake += bet.winAmount();
        System.out.println("Yay! " + stake);
    }
    @Override
    public void lose(Bet bet) {
        //stake -= bet.loseAmount();
        if(stake < bettingAmount )
            isPlaying = false;
        System.out.println("Oh! " + stake);        
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
