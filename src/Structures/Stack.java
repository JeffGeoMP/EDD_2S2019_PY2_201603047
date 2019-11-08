package Structures;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author JeffGeo
 */
public class Stack {
    
    private Node head;
    private Node end;
    
    public Stack(){
        this.head = null;
        this.end = null;
    }
    
    public void add(String Operation, String User){
      Node new_operation = new Node(Operation, User);
        if(Isempty()){
          this.head = new_operation;
          this.end = new_operation;
        }else{
            new_operation.linkednext(this.head);
            this.head = new_operation;
        }  
    }
    
    public void printStack(){
        if(Isempty()){
            System.out.println("Not Found Operations in the System");
        }else{
            Node temp = this.head;
            while(temp!=null){
                System.out.println("Date: "+temp.getDate() +" Hour: "+temp.getHour() + " Operation: "+temp.getOperation()+ " User: "+temp.getUser());
                temp = temp.getNext();
            }
        }
    }
    
    private boolean Isempty(){
        return (null==this.head);
    }
 
}

class Node{
    private String Operation;
    private String User;
    private String Hour;
    private String Date;
    private Node next;
    
    public Node(String Operation, String User){
        this.Date = Date();
        this.Hour = Hour();
        this.Operation = Operation;
        this.User = User;
        this.next = null;
    }

    public String getOperation() {
        return Operation;
    }

    public String getUser() {
        return User;
    }

    public String getHour() {
        return Hour;
    }

    public String getDate() {
        return Date;
    }
    
    private String Hour(){                                          
        Date  date = new Date();
        DateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
        return dateformat.format(date);
    }
    
    private String Date(){
        Date date = new Date();
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        return dateformat.format(date);
    }
    
    public void linkednext(Node n){   
        this.next = n;
    }
    
    public Node getNext(){
        return this.next;
    }
}