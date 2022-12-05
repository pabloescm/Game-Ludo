package PlayerClient;

import java.io.Serializable;

/*
*Path is the implementation of the singly linked list
 */
public class Path implements Serializable {
    public Node startingPoing;
    public Path(){

    }
    public Path(Node startingPoing){
        this.startingPoing=startingPoing;
    }
    public void insert(int nxt){
        Node node=new Node(nxt);
        //if empty
        if(startingPoing==null){
            startingPoing=node;
        }else{//traverse until the last one
            Node end=startingPoing;
            while(end.next!=null){
                end=end.next;
            }
            //since we already traversed up to the last node make the new node the last one
            end.next=node;
        }
    }
}
