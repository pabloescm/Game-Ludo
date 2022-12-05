package PlayerClient;

import java.io.Serializable;

public class Node implements Serializable {
    public int pos;
    public Node next;
    public Node(int pos){
        this.pos=pos;
    }
}
