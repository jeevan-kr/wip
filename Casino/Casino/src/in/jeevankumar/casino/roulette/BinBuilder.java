/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.casino.roulette;

import static in.jeevankumar.casino.roulette.Constants.COMMA;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * BinBuilder creates the Outcomes for all of the 38 individual Bin on the 
 * Roulette Wheel.
 * @author jeevan
 */
public class BinBuilder {
    /**
     * Creates the Outcome instances and uses the addOutcome() method to 
     * place each Outcome in the appropriate Bin of wheel.
     * 
     * @param wheel – The Wheel with Bins that must be populated with Outcomes.
     */
    public void buildBins(Wheel wheel) {
        generateStraightBets(wheel);
        generateSplitBets(wheel);
        generateStreetBets(wheel);
        generateCornerBets(wheel);
        generateLineBets(wheel);
        generateDozenBets(wheel);
        generateColumnBets(wheel);
        generateEvenMoneyBets(wheel);
    }
    
    /**
     * Generate straight bets and add them to the appropriate bin. A Straight 
     * bet is a bet placed on a single number 00,0..36. 
     * 
     * @param wheel 
     */
    private void generateStraightBets(Wheel wheel) {
        
        for(int i = 0; i < 37; i++) {
            Outcome temp = new Outcome(String.valueOf(i), 
                    Constants.StraightBet);
            wheel.get(i).add(temp);
        }
        
        wheel.get(37).add(new Outcome("00", Constants.StraightBet) );
    }
    
    /**
     * Generate split bets and add them to the appropriate bin. There are two 
     * types of split bets (a) the left-right pairs and (b) up down pairs for
     * each number between 1..36 as displayed on the table.
     * 
     * @param wheel 
     */
    private void generateSplitBets(Wheel wheel) {
        generateLeftRightPairSplitBets(wheel);
        generateUpDownPairSplitBets(wheel);
        
    }
    
    private void generateLeftRightPairSplitBets(Wheel wheel) {
        for(int i = 0; i < 12 ; i++) {
            int firstNum = 3 * i + 1;
            int secondNum = firstNum + 1;
            int thirdNum = firstNum + 2;
                
            Outcome leftSplitBet = new Outcome(firstNum + ", "+ secondNum, 
                    Constants.SplitBet );
            
            wheel.get(firstNum).add(leftSplitBet);
            wheel.get(secondNum).add(leftSplitBet);
            
            Outcome rightSplitBet = new Outcome(secondNum + ", "+ thirdNum, 
                    Constants.SplitBet );
            
            wheel.get(secondNum).add(rightSplitBet);
            wheel.get(thirdNum).add(rightSplitBet);
            
        }
    }
    private void generateUpDownPairSplitBets(Wheel wheel) {
        for(int i = 1; i < 10; i +=2) {
            int keyOne = 3 * i + 1;
            int keyTwo = keyOne + 1;
            int keyThree = keyOne + 2;
            
            for(int j = keyOne; i < keyOne + 3; j++) {
                
                int middleNum = j;
                int topNum = j - 3;
                int bottomNum = j + 3;
                //top pair
                Outcome top = new Outcome(topNum + ", " + middleNum, 
                        Constants.SplitBet);
                wheel.get(topNum).add(top);
                wheel.get(middleNum).add(top);
                
                //bottom pair
                Outcome bottom = new Outcome(middleNum + ", " + bottomNum, 
                        Constants.SplitBet);
                wheel.get(bottomNum).add(bottom);
                wheel.get(middleNum).add(bottom);
                
                
            }
        }
        //34, 35, 36 will have only top pairs with 31, 32, 33
                
        for(int i = 34; i < 37; i++) {
            int middleNum = i;
            int topNum = i - 3;
            
            //top pair
            Outcome top = new Outcome(topNum + ", " + middleNum, 
                    Constants.SplitBet);
            wheel.get(topNum).add(top);
            wheel.get(middleNum).add(top);
                
        }
    }

    private void generateStreetBets(Wheel wheel) {
        for(int i = 0; i < 12; i++) {
            int fNum = 3 * i + 1;
            int sNum = fNum + 1;
            int tNum = fNum + 2;
            
            Outcome streetOutcome = new Outcome(fNum + ", " + sNum + ", " + tNum,
                                                Constants.StreetBet);
            wheel.get(fNum).add(streetOutcome);
            wheel.get(sNum).add(streetOutcome);
            wheel.get(tNum).add(streetOutcome);
        }
        
    }

    private void generateCornerBets(Wheel wheel) {
        for(int i = 0; i < 12; i++) {
            int setOneFirst = 3 * i + 1;
            int setOneSecond = setOneFirst + 1;
            int setOneThird = setOneFirst + 3;
            int setOneFourth = setOneFirst + 4;
            String COMMA = ", ";
            StringBuffer sb = new StringBuffer(String.valueOf(setOneFirst));
            sb.append(COMMA).append(setOneSecond)
              .append(COMMA).append(setOneThird)
              .append(COMMA).append(setOneFourth);
            Outcome temp = new Outcome(sb.toString(), Constants.CornerBet);
            
            
            wheel.get(setOneFirst).add(temp);
            wheel.get(setOneSecond).add(temp);
            wheel.get(setOneThird).add(temp);
            wheel.get(setOneFourth).add(temp);
            
            int setTwoFirst = setOneFirst + 1;
            int setTwoSecond = setTwoFirst + 1;
            int setTwoThird = setTwoFirst + 3;
            int setTwoFourth = setTwoFirst + 4;
            sb = new StringBuffer(String.valueOf(setTwoFirst));
            sb.append(COMMA).append(setTwoSecond)
              .append(COMMA).append(setTwoThird)
              .append(COMMA).append(setTwoFourth);
            temp = new Outcome(sb.toString(), Constants.CornerBet);
            
            
            wheel.get(setTwoFirst).add(temp);
            wheel.get(setTwoSecond).add(temp);
            wheel.get(setTwoThird).add(temp);
            wheel.get(setTwoFourth).add(temp);
            
        }
    }

    private void generateLineBets(Wheel wheel) {
        for(int i = 0; i <10; i++) {
            int n = 3 * i + 1;
            String COMMA = ", ";
            StringBuffer sb = new StringBuffer(n);
            for(int j = 1; j < 6; j++) {
                sb.append(COMMA).append(n+j);
            }
            Outcome lineOutcome = new Outcome(sb.toString(), Constants.LineBet);
            
            for(int j = 0; j < 6; j++) {
                wheel.get(n + j).add(lineOutcome);
            }
        }
    }

    private void generateDozenBets(Wheel wheel) {
        String COMMA = ", ";
        String title1 = "First 12";
        String title2 = "Second 12";
        String title3 = "Third 12";
            
        for(int i = 1; i < 25; i += 12) {
            String title;
            
            if(i < 12) 
                title = title1;
            else if (i < 24) 
                title = title2;
            else 
                title = title3;
            
            Outcome dozenOutcome = new Outcome(title, Constants.DozenBet);
            
            for(int j = 0; j < 12; j++) {
                wheel.get(i+j).add(dozenOutcome);
            }
            
        }
    }

    private void generateColumnBets(Wheel wheel) {
        for(int i = 1; i < 35; i += 3) {
            int firstCol = i;
            int secondCol = i + 1;
            int thirdCol = i + 2;
            
            Outcome firstColOutcome = new Outcome("First Column", Constants.ColumnBet);
            Outcome secondColOutcome = new Outcome("Second Column", Constants.ColumnBet);
            Outcome thirdColOutcome = new Outcome("Third Column", Constants.ColumnBet);
            
            for(int j = 0; j < 12; j++) {
                wheel.get(firstCol + j).add(firstColOutcome);
                wheel.get(secondCol + j).add(secondColOutcome);
                wheel.get(thirdCol + j).add(thirdColOutcome);
                        
            }
        }
    }

    private void generateEvenMoneyBets(Wheel wheel) {
        int lowLimit = 19;
        Set<Integer> redSet = new HashSet<>();
        int[] redArr = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
        redSet.addAll(new ArrayList(Arrays.asList(redArr)));
        
        //create outcomes
        
        Outcome oddOutcome = new Outcome("Odd", Constants.EvenMoneyBet);
        Outcome evenOutcome = new Outcome("Even", Constants.EvenMoneyBet);
        
        Outcome lowOutcome = new Outcome("Low", Constants.EvenMoneyBet);
        Outcome highOutcome = new Outcome("High", Constants.EvenMoneyBet);
        
        Outcome redOutcome = new Outcome("Red", Constants.EvenMoneyBet);
        Outcome blackOutcome = new Outcome("Black", Constants.EvenMoneyBet);
        
        for(int i = 1; i < 37; i++) {
            if(i%2 != 0)
                wheel.get(i).add(oddOutcome);
            else 
                wheel.get(i).add(evenOutcome);
            
            if(i < lowLimit)
                wheel.get(i).add(lowOutcome);
            else
                wheel.get(i).add(highOutcome);
            
            if(redSet.contains(i))
                wheel.get(i).add(redOutcome);
            else 
                wheel.get(i).add(blackOutcome);
        }
    }
}
