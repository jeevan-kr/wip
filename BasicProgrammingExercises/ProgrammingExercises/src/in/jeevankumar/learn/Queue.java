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
public class Queue {
    Node first;
    public void enqueue(int n) {
        if(first == null) {
            first = new Node(n);
        } else {
            first.appendToTail(n);
        }
    }
    
    public int dequeue() {
        Node retVal = first;
        if(first!=null) {
            first = first.next;
        }
        return retVal.data;
    }
    
    public String toString() {
        Node n = first;
        StringBuffer sb = new StringBuffer();
        while(n!=null) {
            sb.append(n.data).append(" <- ");
            n = n.next;
        }
        return sb.toString();
    }
}
