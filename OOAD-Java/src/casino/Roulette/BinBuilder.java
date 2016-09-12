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

import java.time.Duration;
import java.time.LocalTime;
import java.util.Vector;

/**
 *
 * @author Jeevan
 */
public class BinBuilder {
    Vector<Bin> bins;
    private static String COMMA = ",";
    private static String SPACE = " ";
    private static int timeCount = 1;
    private static BinBuilder instance;
    /**
     * Want a single instance of BinBuilder. Hence, the constructor is marked 
     * private.
     * 
     */
    private BinBuilder() {
        
    }
    
    /**
     * Returns the only instance of BinBuilder.
     * @return (BinBuilder) - instance of the BinBuilder
     */
    public static BinBuilder getInstance() {
        if(instance == null) {
            instance = new BinBuilder();
        }
        return instance;
    }
    
    /**
     * Generates the Bin s with the corresponding Outcome to the given 
     * instance of the Wheel. 
     * @param wheel (Wheel) - The wheel to which the Bin s will be added. 
     */
    public void buildBins(Wheel wheel) {
        
        this.addStraightBets(wheel);
        this.addSplitBets(wheel);
        this.addStreetBets(wheel);
        this.addCornerBets(wheel);
        this.addLineBets(wheel);
        this.addDozenBets(wheel);
        this.addColumnBets(wheel);
        this.addLowHighBet(wheel);
        this.addRedBlackBets(wheel);
        this.addEvenOddBets(wheel);
        this.addFiveBet(wheel);
    }
    
    /**
     * Add all the Straight Bets.
     * 
     * @param wheel (Wheel) - The wheel to which the Outcomes will be added.  
     */
    private void addStraightBets(Wheel wheel) {
        for(int i = 0; i < 38; i++) {
            String name = "" + i;
            if(i == 37)
                name = "00";
            Outcome temp = new Outcome( name, 35 );
            wheel.addOutcome(i, temp);
        }
    }
    
    /**
     * Add all the Dozen Bets.
     * 
     * @param wheel (Wheel) - The wheel to which the Outcomes will be added.  
     */
    private void addDozenBets(Wheel wheel) {
        Outcome firstDozenOutcome = new Outcome( "First Dozen", 2 );
        Outcome secondDozenOutcome = new Outcome( "Second Dozen", 2 );
        Outcome thirdDozenOutcome = new Outcome( "Third Dozen", 2 );
        
        for(int i = 1; i <= 36; i++) {
            
            if( i <= 12) {
                //bins.get(i).add(firstDozenOutcome);
                wheel.addOutcome(i, firstDozenOutcome);
            } else if ( i >= 13 && i <= 24) {
                wheel.addOutcome(i, secondDozenOutcome);
            } else {
                wheel.addOutcome(i, thirdDozenOutcome);
            }
        }
    }
    private void addColumnBets(Wheel wheel) {
        Outcome firstColOutcome = new Outcome( "First Column", 2 );
        Outcome secondColOutcome = new Outcome( "Second Column", 2 );
        Outcome thirdColOutcome = new Outcome( "Third Column", 2 );
        
        for(int i = 1; i <=36; i++) {
            switch(i%3) {
                case 0:
                    wheel.addOutcome(i, thirdColOutcome);
                    break;
                case 1:
                    wheel.addOutcome(i, firstColOutcome);
                    break;
                case 2:
                    wheel.addOutcome(i, secondColOutcome);
                    break;
            }
        }
    }
    private void addLowHighBet(Wheel wheel) {
        Outcome lowOutcome = new Outcome( "Low", 1 );
        Outcome highOutcome = new Outcome( "High", 1 );

        String outcomeName;
        for(int i = 1; i <= 36; i++) {
            
            if( i <= 18) {
                wheel.addOutcome(i, lowOutcome);
            } else {
                wheel.addOutcome(i, highOutcome);
            }
            
        }
    }
    
    private void addRedBlackBets(Wheel wheel) {
        Outcome redOutcome = new Outcome( "Red", 1 );
        Outcome blackOutcome = new Outcome( "Black", 1 );
        for(int i = 1; i <= 36; i++) {
            if(i == 1 || i == 3  || i == 5  || i == 7  || i == 9 
                      || i == 12 || i == 14 || i == 16 || i == 18
                      || i == 19 || i == 21 || i == 23 || i == 25 
                      || i == 27 || i == 30 || i == 32 || i == 34
                      || i ==36) {
                wheel.addOutcome(i, redOutcome);
            } else {
                wheel.addOutcome(i, blackOutcome);
            }
        }
    }
    
    private void addStreetBets(Wheel wheel) {
        String comma = ",";
        String space = " ";
        StringBuilder outcomeName = new StringBuilder();
        for(int i = 1; i <= 34; i+=3) {
            outcomeName.append(i).append(comma).append(space);
            outcomeName.append(i+1).append(comma).append(space);
            outcomeName.append(i+2);
            Outcome outcome = new Outcome( outcomeName.toString(), 11 );
            wheel.addOutcome(i, outcome);
            wheel.addOutcome(i+1, outcome);
            wheel.addOutcome(i+2, outcome);
            outcomeName.setLength(0);
        }
    }
    
    private void addLineBets(Wheel wheel) {
        String comma = ",";
        String space = " ";
        StringBuilder outcomeName = new StringBuilder();
        for(int i = 1; i <= 31; i+=3) {
            outcomeName.append(i).append(comma).append(space);
            outcomeName.append(i+1).append(comma).append(space);
            outcomeName.append(i+2).append(comma).append(space);
            outcomeName.append(i+3).append(comma).append(space);
            outcomeName.append(i+4).append(comma).append(space);
            outcomeName.append(i+5);
            Outcome outcome = new Outcome( outcomeName.toString(), 5 );
            for(int j = i; j <= i+5; j++) {
                wheel.addOutcome(j, outcome);
            }
            outcomeName.setLength(0);
        }
    }
    private void addEvenOddBets(Wheel wheel) {
        Outcome evenOutcome = new Outcome( "Even", 1 );
        Outcome oddOutcome = new Outcome( "Odd", 1 );
        for (int i = 1; i <= 36; i++) {
            if(i%2==0) {
                wheel.addOutcome(i, evenOutcome);
            } else {
                wheel.addOutcome(i, oddOutcome);
            }
        }
        
    }
    private void addSplitBets(Wheel wheel) {
        
        for(int i = 1; i <= 31; i+=3) {
            //top-left and top-middle
            setOutcome(wheel, i,i+1,17);
            
            //top-left and bottom-left
            setOutcome(wheel, i,i+3,17);
            
            //top-middle and top-right
            setOutcome(wheel, i+1,i+2,17);
            
            //top-middle and bottom-middle
            setOutcome(wheel, i+1,i+1+3,17);
            
            //top-right and bottom-right
            setOutcome(wheel, i+2,i+2+3,17);
        }
    }
    
    private void setOutcome(Wheel wheel,int firstBin, int secondBin, int odds) {
        StringBuilder outcomeName = new StringBuilder();
        outcomeName.append(firstBin).append(BinBuilder.COMMA).append(BinBuilder.SPACE);
        outcomeName.append(secondBin);
        
        Outcome outcome = new Outcome( outcomeName.toString(), odds );
        wheel.addOutcome(firstBin, outcome);
        wheel.addOutcome(secondBin, outcome);
    }
    
    private void setOutcome(Wheel wheel, int firstBin, int secondBin, int thirdBin, int fourthBin, int odds) {
        StringBuilder outcomeName = new StringBuilder();
        outcomeName.append(firstBin).append(BinBuilder.COMMA).append(BinBuilder.SPACE);
        outcomeName.append(secondBin).append(BinBuilder.COMMA).append(BinBuilder.SPACE);
        outcomeName.append(thirdBin).append(BinBuilder.COMMA).append(BinBuilder.SPACE);
        outcomeName.append(fourthBin);
        
        Outcome outcome = new Outcome( outcomeName.toString(), odds );
        wheel.addOutcome(firstBin, outcome);
        wheel.addOutcome(secondBin, outcome);
        wheel.addOutcome(thirdBin, outcome);
        wheel.addOutcome(fourthBin, outcome);
        
    }
    
    private void addCornerBets(Wheel wheel) {
        for(int i = 1; i <= 31; i+=3) {
            //Ex: 1,2,4,5
            setOutcome(wheel, i,i+1,i+3,i+4, 8);
            
            //Ex: 2,3,5,6
            setOutcome(wheel, i+1,i+2,i+4,i+5, 8);
            
        }
    }

    private void addFiveBet(Wheel wheel) {
        String outcomeName = "Five Bet";
        //37 corresponds to "00"
        int[] keyBins = {0,1,2,3,37};
        Outcome outcome = new Outcome( outcomeName, 6 );
        
        for(int i = 0; i < keyBins.length; i++) {
            wheel.addOutcome(keyBins[i], outcome);
        }
    }
}
