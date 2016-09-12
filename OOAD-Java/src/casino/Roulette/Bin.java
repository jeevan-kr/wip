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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jeevan
 */
public class Bin {
    Set<Outcome> outcomes;
    public Bin() {
        this.outcomes = new HashSet();
    }
    
    public Bin(Collection outcomes) {
        this.outcomes = new HashSet(outcomes);
    }
    
    public Bin(Outcome[] outcomes) {
        Set temp = new HashSet();
        for(Outcome outcome : outcomes) {
            temp.add(outcome);
        }
        this.outcomes = temp;
    }
    
    public void add(Outcome outcome) {
        if(outcomes == null) {
            System.out.println("outcomes are null");
        }
        this.outcomes.add(outcome);
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer("[ ");
        for(Outcome outcome : outcomes) {
            sb.append(outcome.toString()).append(" , ");
        }
        sb.append("]");
        return sb.toString();
    }
}
