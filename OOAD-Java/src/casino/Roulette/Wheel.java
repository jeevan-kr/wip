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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Jeevan
 */
public class Wheel {
    Vector<Bin> bins;
    Random rng;
    Map<String, Outcome> outcomeMap;
    public Wheel(Random rand) {
        this.rng = rand;
        initializeBins();
    }
    
    public Wheel() {
        this.rng = new Random();
        initializeBins();
    }
    private void initializeBins() {
        this.bins = new Vector(38);
        for(int i = 0; i < 38; i++) {
            this.bins.add(new Bin());
        }
    }
    
    public void addOutcome(int binIndex, Outcome outcome) {
        Bin temp = bins.get(binIndex);
        temp.add(outcome);
        if(outcomeMap == null)
            outcomeMap = new HashMap();
        outcomeMap.put(outcome.name, outcome);
    }
    public Outcome getOutcome(String outcomeName) {
        return outcomeMap.get(outcomeName);
    }
    public Bin next() {
        int winningBin = rng.nextInt(38);
        return getBin(winningBin);
    }
    public Bin getBin(int index) {
        return bins.get(index);
    }
}
