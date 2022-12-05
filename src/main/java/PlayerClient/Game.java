package PlayerClient;

import GUI.Board;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;


public class Game implements Serializable{
    public static final long serialVersionUID = 42L;
    PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public Player blue = new Player("blue",1);
    public Player yellow = new Player("yellow",2);
    public Player red = new Player("red",3);
    public Player green = new Player("green",4);

    public int turn=1;

    public int numPlayers=0;
    public int currentRoll=0;

    public Game(){
        initYellowPlayerPath();
        initBluePlayerPath();
        initRedPlayerPath();
        initGreenPlayerPath();

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public Player getCurrentPlayer(){

        switch (turn){
            case 1:
                return blue;
            case 2:
                return yellow;
            case 3:
                return red;
            case 4:
                return green;
        }

        return null;

    }

    public void simulateYellow(){
        Node current=yellow.path.startingPoing;
        while (current.next!=null){
            Board.grid.get(current.pos).setColor(Color.YELLOW);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(current.pos+"->");
            current=current.next;
        }

    }


    public int getTurn() {
        propertyChangeSupport.firePropertyChange("game", 1, 2);
        return turn;
    }

    public void initAllPaths(){
        initYellowPlayerPath();
        initBluePlayerPath();
        initRedPlayerPath();
        initGreenPlayerPath();
    }

    public void initYellowPlayerPath(){
        for(int i=0;i<Initializer.yellowPath.length;i++){
            yellow.path.insert(Initializer.yellowPath[i]);
        }
    }
    public void initBluePlayerPath(){
        for(int i=0;i<Initializer.bluePath.length;i++){
            blue.path.insert(Initializer.bluePath[i]);
        }
    }

    public void initRedPlayerPath(){
        for(int i=0;i<Initializer.redPath.length;i++){
            red.path.insert(Initializer.redPath[i]);
        }
    }
    public void initGreenPlayerPath(){
        for(int i=0;i<Initializer.greenPath.length;i++){
            green.path.insert(Initializer.greenPath[i]);
        }
    }



}
