
package Visualization;

public class Queue {
    Node front;
    Node rear;
    int size;
    
    public Queue(){
        this.front = null;
        this.rear = null;
        this.size = 0;
    }
    
    public boolean isEmpty(){
        return (this.front==null);
    }
    
    public void enqueue(char data){
        Node n = new Node(data);
        if(this.front == null){
            this.front = n;
            this.rear = n;
            size++;
            return;
        }
        this.rear.next = n;
        this.rear = n;
        this.rear.next=front;
        size++;
    }
    
    public void dequeque(){
        if(isEmpty()){
            System.out.println("Queue is empty.");
            return;
        }
        this.front = this.front.next;
        size--;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public void peek(){
        if(isEmpty()){
            System.out.println("Queue is Empty.");
            return;
        }
        System.out.println(front.data);
    }
    
    public int getFront(){
        return front.data;
    }
    
    public void display(){
        if(isEmpty()) System.out.println("Queue is Empty.");
        Node temp = this.front;
        while(temp.next!=front){
            System.out.print(temp.data+"-");
            temp = temp.next;
        }
        System.out.println(temp.data);
    }
}
