/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.learn;

import junit.framework.*;
import junit.framework.Test;
/**
 *
 * @author jeevan
 */
public class ProgrammingExercisesTest extends TestCase {
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testIsUniqueCharacters() {
        ProgrammingExercises pe = new ProgrammingExercises();
        
        assertTrue(pe.hasOnlyUniqueCharacters("abcdefghABC"));
        assertTrue(pe.hasOnlyUniqueCharacters("abcdefghijklmn1238904"));
        assertFalse(pe.hasOnlyUniqueCharacters("abcdefghijklmAwxyzABCDEG"));
        
    }
    
    
    public void testReverse() {
        
        ProgrammingExercises pe = new ProgrammingExercises();
        
        assertTrue(pe.reverse("abcd").equals("dcba"));
        assertTrue(pe.reverse("").equals(""));
        assertTrue(pe.reverse("a").equals("a"));
        
        assertFalse(pe.reverse("a").equals("b"));
        assertTrue(pe.reverse("bc").equals("cb"));
        assertTrue(pe.reverse("abcde").equals("edcba"));
        assertTrue(pe.reverse("abcdef").equals("fedcba"));
        assertTrue(pe.reverse("abcdefg").equals("gfedcba"));
    }
    
    
    
    public void testReplaceSpaceWith() {
        ProgrammingExercises pe = new ProgrammingExercises();
        assertTrue(pe.replaceSpaceWith("abd def  ", "%20")
                .equals("abd%20def"));
        assertTrue(pe.replaceSpaceWith("abd def qde    ", "%20")
                .equals("abd%20def%20qde"));
    }
    
    
    
    public void testArePermutationsOfEachOther() {
        ProgrammingExercises pe = new ProgrammingExercises();
        
        assertTrue(pe.arePermutationsOfEachOther("", ""));
        assertFalse(pe.arePermutationsOfEachOther("", "a"));
        
        assertTrue(pe.arePermutationsOfEachOther("a", "a"));
        
        assertTrue(pe.arePermutationsOfEachOther("dcba", "acbd"));
        assertTrue(pe.arePermutationsQuick("dcaba", "acbda"));
    }
    
    
    public void testCompress() {
        ProgrammingExercises pe = new ProgrammingExercises();
        assertEquals(pe.compress("aaaaaaaab"), "a8b1");
        assertEquals(pe.compress("aa"), "aa");
        assertEquals(pe.compress("aabbbbbccccc"), "a2b5c5");
        assertEquals(pe.compress(""), "");
    }
    
    public void testCompressSimple() {
        ProgrammingExercises pe = new ProgrammingExercises();
        assertEquals(pe.compressSimple("aaaaaaaab"), "a8b1");
        assertEquals(pe.compressSimple("aa"), "aa");
        assertEquals(pe.compressSimple("aabbbbbccccc"), "a2b5c5");
        assertEquals(pe.compressSimple(""), "");
    }
    
    public void testResetRowsColumns() {
        ProgrammingExercises pe = new ProgrammingExercises();
        int[][] mat  = {{0,1,2},{1,5,3},{4,5,0}
        };
        int[][] opt = {{0,0,0},{0,5,0},{0,0,0}};
        mat = pe.resetRowsColumns(mat);
        assertTrue(isEqual(mat,opt));
        
        int[][] mat2  = {{1,1,2},{1,5,3},{4,5,0}};
        int[][] opt2 = {{1,1,0},{1,5,0},{0,0,0}};
        mat2 = pe.resetRowsColumns(mat2);
        
        assertTrue(isEqual(mat2,opt2));
        
        int[][] mat3  = {{1,1,2},
                         {1,5,3},
                         {4,5,5}};
        int[][] opt3 = {{1,1,2},
                        {1,5,3},
                        {4,5,5}};
        mat3 = pe.resetRowsColumns(mat3);
        assertTrue(isEqual(mat3,opt3));
        
    }
    
    private boolean isEqual(int[][] a, int[][] b) {
        boolean retVal = true;
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[i].length; j++) {
                try {
                    if(a[i][j] != b[i][j]) {
                        retVal = false;
                        break;
                    }
                } catch (Exception ex) {
                    retVal = false;
                    break;
                }
                    
            }
        }
        return retVal;
    }
    
    public void testIsRotation() {
        ProgrammingExercises pe = new ProgrammingExercises();
        assertTrue(pe.isRotation("jeevan", "evanje"));
        assertTrue(pe.isRotation("jeevan", "njeeva"));
    }
}
