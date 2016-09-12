/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.learn;

/**
 *
 * @author Jeevan
 */
public class Stack {
    Node top;
    public Node pop() {
        Node retVal = top;
        if(top != null) {
            top = top.next;
        }
        return retVal;
    }
    
    public void push(int n) {
        if(top == null) {
            top = new Node(n);
        } else {
            Node node = top;
            top = new Node(n);
            top.next = node;
        }
    }
    
    public int peek() {
        return top.data;
    }
    
    public String toString() {
        Node n = top;
        StringBuffer sb = new StringBuffer();
        while(n!=null) {
            sb.append(n.data).append(" <- ");
        }
        
        return sb.toString();
    }
}
