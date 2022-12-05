package PlayerClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Player implements Comparable<Player>, Serializable {
    public String username=null;
    public String color;
    public Path path;
    public ArrayList<Pawn> pawnList=new ArrayList<>();



    private int turn;
    public Player(String color,int turn){
        this.turn=turn;
        this.color=color;
        path=new Path();
        pawnList.add(new Pawn(-1,this));
        pawnList.add(new Pawn(-1,this));
        pawnList.add(new Pawn(-1,this));
        pawnList.add(new Pawn(-1,this));
    }

    public boolean executeMove(){
        return false;
    }
    public int rollDice(){
        Random random=new Random();
        return random.nextInt(7-1)+1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public ArrayList<Pawn> getPawnList() {
        return pawnList;
    }

    public void setPawnList(ArrayList<Pawn> pawnList) {
        this.pawnList = pawnList;
    }



    public int getTurn() {
        return turn;
    }


    @Override
    public int compareTo(Player o) {
        return this.turn-o.turn;
    }
}
