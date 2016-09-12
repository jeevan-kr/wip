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
public class Outcome {
    String name;
    int odds;
    /**
     * Constructor for the Outcome class that takes name (String) and odds(int)
     * of the given Outcome
     * @param name (String) The name of this outcome
     * @param odds (int) The payout odds of this outcome
     */
    public Outcome(String name, int odds) {
        this.name = name;
        this.odds = odds;
    }
    
    /**
     * 
     * @param amount (int) - amount that has been bet
     * @return winning amount based on bet amount and odds
     */
    public int winAmout(int amount) {
        return amount * this.odds;
    }
    
    public boolean equals(Object other) {
        Outcome otherOutcome = (Outcome) other;
        return this.name.equals(otherOutcome.name) 
                && (this.odds == otherOutcome.odds);
    }
    
    public String toString() {
        
        return (this.name + " ( " + this.odds + ":1 )");
    }
}
