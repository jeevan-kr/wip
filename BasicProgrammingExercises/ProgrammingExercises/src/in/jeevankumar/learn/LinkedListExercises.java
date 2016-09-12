/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.learn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jeevan
 */
public class LinkedListExercises {
    public Node removeDuplicates(Node head) {
        Set<Integer> set = new HashSet();
        
        Node n = head;
        
        while(n != null) {
            set.add(n.data);
            n = n.next;
        }
        Node prev = null;
        Node x;
        head = null;
        for(Integer val : set) {
            x = new Node(val);
            if(head == null) {
                head = x;
            } else {
                prev.next = x;
            }
            prev = x;
        }
        return head;
    }
    
    public Node createLinkedList(int[] list) {
        Node head = null;
        Node n, prev = null;
        for(int i = 0; i < list.length; i++) {
            n = new Node(list[i]);
            if(head == null) {
                head = n;
            } else {
                prev.next = n;
            }
            prev = n;
        }
        return head;
    }
    
    public Node getKthLastElement(Node head, int k) {
        Node n = head;
        while(k > 0) {
            if(n.next != null) {
                n = n.next;
            } else {
                return null;
            }
            k--;
        }
        Node runner = head;
        
        while(n != null) {
            runner = runner.next;
            n = n.next;
        }
        return runner;
    }
    
    public String add(String a, String b) {
        Node aNum = createReverseList(a);
        Node bNum = createReverseList(b);
        Node longerHead, shorterHead;
        
        if(a.length() > b.length()) { 
            longerHead = aNum;
            shorterHead = bNum;
        }
        else { 
            longerHead = bNum;
            shorterHead = aNum;
        }
        Node prev = null;
        Node n = longerHead;
        int carry = 0;
        while(shorterHead != null) {
            n.data = n.data + shorterHead.data + carry;
            if(n.data >= 10) {
                carry = 1;
                n.data = n.data%10;
            } else {
                carry = 0;
            }
            prev = n;
            n = n.next;
            shorterHead = shorterHead.next;
        }
        if(carry!=0 && n==null) {
            longerHead.appendToTail(carry);
            carry = 0;
        } else if (carry!=0 && n!=null) {
            while(n!=null) {
                n.data = n.data + carry;
                if(n.data > 10) {
                    carry = 1;
                    n.data = n.data%10;
                } else {
                    carry = 0;
                }
                prev = n;
                n = n.next;
            }
        }
        
        if(n==null && carry!=0) {
            longerHead.appendToTail(carry);
        }
        return reversePrint(longerHead);
        //return longerHead.toString();
    }
    private Node createReverseList(String a) {
        if(a == null || a.length() <= 0 ) {
            return null;
        }
        char[] digits = a.toCharArray();
        
        Node head = new Node(digits[digits.length -1] - '0');
        Node prev = head;
        for(int i = digits.length - 2; i > -1; i--) {
            Node n = new Node(digits[i]- '0');
            prev.next = n;
            prev = n;
        }
        return head;
    }
    
    private String reversePrint(Node head) {
        if(head !=null) {
            return reversePrint(head.next) + String.valueOf(head.data);
        } else {
            return "";
        }
    }
    
    public boolean isPalindrome(Node head) {
        List<Node> list = new ArrayList<Node>();
        Node n = head;
        while(n != null) {
            list.add(n);
            n = n.next;
        }
        int i, j;
        for(i = 0, j = list.size() - 1; i < list.size(); i++, j--) {
            if(list.get(i).data != list.get(j).data) {
                return false;
            }
        }
        return true;
    }
}
