/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.learn;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author jeevan
 */
public class ProgrammingExercises {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ProgrammingExercises pe = new ProgrammingExercises();
        pe.run();
    }
    
    public void run() {
        
    }
    
    //1. Write a program to determine if a character array has only unique
    //   characters. If you could not use extra space what would you do?
    public boolean hasOnlyUniqueCharacters(String input) {
        //char in java is 16 bit. Max no. of characters is 2^15 - 1 = 65,536
        //if ASCII is the characterset, then max no. of unique ASCII characters
        // is 256
        char[] inputChar = input.toCharArray();
        boolean retVal = true;
        if(input.length() < (Math.pow(2.0, 15.0) - 1))  {
            BigInteger checker;
            checker = new BigInteger("0");
            for(int i = 0; i < input.length(); i++) {
                int val = input.charAt(i) ;
                if(checker.testBit(val)) {
                    retVal = false;
                    break;
                }
                checker = checker.setBit(val);
            }
            
        }
        
        return retVal;
    }
    //2. Write a program to reverse a null terminated char array 
    public String reverse(String input) {
        char[] inputChar = input.toCharArray();
        for(int i = 0, j = input.length()-1; i < input.length() / 2 ; i++, j--) {
            char temp = inputChar[i];
            inputChar[i] = inputChar[j];
            inputChar[j] = temp;
            
        }
        String output = new String(inputChar);
        return output;
    }
    
    //3. Write a program to test whether a string is permutation of another
    public boolean arePermutationsOfEachOther(String first, String second) {
        boolean retVal = false;
        if (first.length() == second.length()) {
            char[] firstChar = first.toCharArray();
            char[] secondChar = second.toCharArray();
            firstChar = this.sort(firstChar);
            secondChar = this.sort(secondChar);
            retVal = true;
            for (int i = 0; i < first.length(); i++) {
                if(firstChar[i] != secondChar[i]) {
                    retVal = false;
                    break;
                }
            }
        }
        return retVal;
    }
    
    public boolean arePermutationsQuick(String first, String second) {
        boolean retVal = true;
        HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
        char[] firstChar = first.toCharArray();
        
        for(int i = 0; i < firstChar.length; i++) {
            if(charCountMap.containsKey(firstChar[i])) {
                Integer count = charCountMap.get(firstChar[i]);
                count++;
                charCountMap.put(firstChar[i], count);
            } else {
                charCountMap.put(firstChar[i], new Integer(1));
            }
        }
        char[] secondChar = second.toCharArray();
        
        for(int i = 0; i < secondChar.length; i++) {
            if(charCountMap.containsKey(secondChar[i])) {
                Integer count = charCountMap.get(secondChar[i]);
                count = count - 1;
                charCountMap.put(secondChar[i], count);
            } else {
                retVal = false;
                break;
            }
        }
        
        if(retVal) {
            Set<Character> keySet = charCountMap.keySet();
            for(Character key : keySet) {
                Integer count = charCountMap.get(key);
                if (count != 0) {
                    retVal = false;
                    break;
                }
            }
        }
        return retVal;
        
    }
    
    public char[] sort(char[] input) {
        
        for(int i = 0; i < input.length; i++) {
            int j = i ;
            boolean moved = false;
            while( (j > 0 ) && input[j] < input[j-1]){
                char temp;
                temp = input[j - 1]; 
                input[j-1] = input[j];
                input[j] = temp;
                j--;
            }
            
        }
        return input;
    }
    
    //4. Replace all the spaces in a given with '%20' assume there is enough spaces at the 
    //end of the string to accomodate all the '%20'
    
    public String replaceSpaceWith(String input, String replacement) {
        char[] inputChar = input.toCharArray();
        char[] replacementChar = replacement.toCharArray();
        int j = input.length() - 1;
        boolean flag = false;
        for (int i = input.length() - 1; i >= 0; i--) {
            if(inputChar[i] != ' ') {
                inputChar[j--] = inputChar[i];
                flag = true;
            } else if (inputChar[i] == ' ' && flag) {
                for (int k = replacement.length() - 1; k >= 0; ) {
                    inputChar[j--] = replacementChar[k--];
                }
            } 
        }
        
        String output = new String(inputChar);
        return output;
    }
    
    //5. Implement a method to do basic string compression 'aaaabbcccccc' to 
    //   a4b2c6. If compression is no smaller than the original string 
    //   
    public String compress(String input) {
        String retVal = null;
        
        List<Character> compressedList = new LinkedList<Character>();
        List<Integer> compressedCount = new LinkedList<Integer>();
        int count = 0;
        char[] inputChar = input.toCharArray();
        
        char currentChar;
        if (inputChar.length > 0) {
            currentChar = inputChar[0];
        
            for(int i = 1; i <= inputChar.length; ) {
                count = 1;
                int j = i;

                while(j < inputChar.length && currentChar == inputChar[j]) {
                    count++;
                    j++;
                }

                compressedList.add(currentChar);
                compressedCount.add(count);
                if (j < inputChar.length) {
                    currentChar = inputChar[j];
                }
                i = j + 1 ;
            }
        }
        
        char[] output;
        if(compressedList.size() * 2 >= input.length()) {
            retVal = input;
        } else {
            
            output = new char[compressedList.size() * 2];
            for(int i = 0, j = 0; i < output.length; i += 2, j++) {
                output[i] = compressedList.get(j);
                output[i+1] = (char) (compressedCount.get(j).intValue() + '0');
            }
            
            retVal = new String(output);
        }
        //System.out.println("o " + retVal);
        return retVal;
    }
    
    //1.5
    public String compressSimple(String str) {
        String retVal = str;
        
        char[] strChar = str.toCharArray();
        if(str.length() > 1) {
            char last = strChar[0];
            StringBuffer output = new StringBuffer();
            int count = 1;
            for(int i = 1; i < strChar.length; i++) {

                if(strChar[i] == last) {
                    count++;
                } else {
                    output.append(last).append(count);
                    last = strChar[i];
                    count = 1;
                }
            }
            output.append(last).append(count);
            //System.out.println(output.toString());

            if (output.length() < str.length()) {
                retVal = output.toString();
            }
        }
        return retVal;
    }
    
    //1.6 Rotate n*n matrix in-place
    //    
    public int[][] rotateMatrix(int[][] input) {
        int[][] output = null;
        if(input[0].length == input.length) {
            
        }
        return output;
    }
    
    /**
     * Set all row values to 0 if it contains 0. Set all columns to 0 if it 
     * contains 0.
     */
    public int[][] resetRowsColumns(int[][] input) {
        List<Pos> positions = new ArrayList();
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[i].length; j++) {
                if(input[i][j] == 0) {
                    Pos pos = new Pos(i,j);
                    positions.add(pos);
                }
            }
        }
        for(Pos pos : positions) {
            int row = pos.row;
            int col = pos.col;
            
            input = resetRow(row, input);
            input = resetCol(col, input);
        }
        return input;
    }
    
    private class Pos {
        int row;
        int col;
        public Pos(int i, int j) {
            this.row = i;
            this.col = j;
        }
    }
    
    private int[][] resetRow(int row, int[][] input) {
        for(int j = 0; j < input[row].length; j++) {
            input[row][j] = 0;
        }
        return input;
    }
    private int[][] resetCol(int col, int[][] input) {
        for(int j = 0; j < input.length; j++) {
            input[j][col] = 0;
        }
        return input;
    }
    
    public boolean isRotation(String source, String str) {
        boolean retVal = true;
        String testString = source + source;
        //System.out.println(testString);
        if(testString.indexOf(str)< -1) {
            retVal = false;
        }
        return retVal;
    }
    
    

}
