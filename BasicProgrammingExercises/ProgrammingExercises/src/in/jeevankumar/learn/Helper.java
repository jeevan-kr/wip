/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.learn;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jeevan
 */
public class Helper {
    public static void printMap(Map<Character,Integer> input) {
        Set<Character> keySet = (Set<Character>)input.keySet();
        
        Iterator it = keySet.iterator();
        while(it.hasNext()) {
            Character key = (Character) it.next();
            Integer count = input.get(key);
            
            System.out.println(key + " " + count);
        }
    }

    static void printMatrix(int[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j <mat[i].length;j++) {
                System.out.print(mat[i][j] + ",");
            }
            System.out.println("");
        }
    }
}
