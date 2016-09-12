/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.learn;

import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;

/**
 *
 * @author Jeevan
 */
public class LinkedListExercisesTest extends TestCase {
    public void testRemoveDuplicates() {
        int[] input = {1,23,4,5,1,2,3};
        LinkedListExercises lle = new LinkedListExercises();
        Node head = lle.createLinkedList(input);
        //System.out.println(head.toString());
        head = lle.removeDuplicates(head);
        int[] comp = {1,23,4,5,2,3};
        Set<Integer> compSet = new HashSet();
        compSet.addAll(compSet);
        //System.out.println(head.containsSameData(compSet));
    }
    
    public void testGetKthLastElement() {
        int[] input = {1,2,3,4,5,6,7,8,9,10};
        LinkedListExercises lle = new LinkedListExercises();
        Node head = lle.createLinkedList(input);
        //System.out.println(head.toString());
        
        assertEquals(lle.getKthLastElement(head, 4).data,7);
        
    }
    public void testRemoveNode() {
        int[] input = {1,2,3,4,5,6,7,8,9,10};
        int[] compare = {1,2,3,5,6,7,8,9,10};
        LinkedListExercises lle = new LinkedListExercises();
        Node head = lle.createLinkedList(input);
        //System.out.println(head.toString());
        Node n = head.next.next.next;
        n.removeNode();
        assertTrue(head.containsSameOrderedData(compare));
    }
    
    public void testAdd() {
        LinkedListExercises lle = new LinkedListExercises();
        //assertTrue(lle.add("35", "55").equals("0 -> 9 -> "));
        //String out = lle.add("35", "55");
        assertTrue(lle.add("10","").equals("10"));
        assertTrue(lle.add("0","").equals("0"));
        assertTrue(lle.add("","").equals(""));
        assertTrue(lle.add("35","55").equals("90"));
        assertTrue(lle.add("45","55").equals("100"));
        assertTrue(lle.add("999","999").equals("1998"));
        assertTrue(lle.add("9","1000").equals("1009"));
        assertTrue(lle.add("1000","9").equals("1009"));
        assertTrue(lle.add("9123091098301928301923", "92834093204820348232309423049234329423948023948")
                .equals("92834093204820348232309432172325427725876325871"));
    }
    
    @SuppressWarnings("empty-statement")
    public void testIsPalindrome() {
        LinkedListExercises lle = new LinkedListExercises();
        int[] input = {1,2,2,1};
        Node head = lle.createLinkedList(input);
        assertTrue(lle.isPalindrome(head));
        
        int[] input2 = {1,2,2,3};
        head = lle.createLinkedList(input2);
        assertFalse(lle.isPalindrome(head));
        
        int[] input3 = {1,2,3,2,1};
        head = lle.createLinkedList(input3);
        assertTrue(lle.isPalindrome(head));
        
        int[] input4 = {1,2,2,23,33,11,1,1};
        head = lle.createLinkedList(input4);
        assertFalse(lle.isPalindrome(head));
        
    }
}
