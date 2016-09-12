/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.learn;

import java.util.Set;

/**
 *
 * @author Jeevan
 */
public class Node {
    Node next = null;
    int data;
    
    public Node (int i ) {
        data = i;
    }
    void appendToTail(int d) {
        Node node = new Node(d);
        Node n = this;
        while(n.next != null) {
            n = n.next;
        } 
        n.next = node;
    }
    public Node deleteNode(Node head, int i) {
        Node n = head;
        if(n.data == i) {
            head = n.next;
        } else {
            Node prev = n;
            while(n.next!=null) {
                if(n.next.data == i) {
                    n.next = n.next.next;
                    break;
                }
            }
        }
        return head;
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer("");
        Node n = this;
        sb.append(n.data).append(" -> ");
        while(n.next != null) {
            sb.append(n.next.data).append(" -> ");
            n = n.next;
        }
        return sb.toString();
    }
    
    public void removeNode() {
        Node n = this;
        while(n.next.next != null) {
            n.data = n.next.data;
            n = n.next;
        }
        n.data = n.next.data;
            
        n.next = null;
    }
    
    public boolean containsSameOrderedData(int[] list) {
        Node n = this;
        int i = 0;
        boolean retVal = true;
        while(n.next == null) {
            if(n.data != list[i++]) {
                retVal = false;
                break;
            }
            n = n.next;
        }
        return retVal;
    }
    
    public boolean containsSameData(Set<Integer> input) {
        boolean retVal = true;
        Node n = this;
        int i = 0;
        while(n.next != null) {
            if(!input.contains(n.data)) {
                //System.out.println("this is false "+ n.data + " " + i );
                retVal = false;
                break;
            } else {
                input.remove(n.data);
            }
            n = n.next;
            i++;
        }
        return retVal;
    }
}
