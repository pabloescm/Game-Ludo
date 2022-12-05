package GUI;

import PlayerClient.Player;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Grid extends JPanel implements Serializable {
    int x=3;
    int y=4;
    /*
    *Add squares to the actual grid containing the path
     */
    public Grid(int x,int y){
        this.x=x;
        this.y=y;
        this.setLayout(new GridLayout(x,y));
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                Square square=new Square(Board.counter);
                Board.grid.add(square);
              //  square.add(new JLabel(String.valueOf(Board.counter)));//adding a jlabel to identify the current index of the square in the grid
                Board.counter++;
                this.add(square);
            }
        }
    }
    /*
    *Add Squares to the base container
     */
    public Grid(String icon, Player player, ArrayList<Square> home){
        Dimension dimension=new Dimension(75,75);

        this.x=2;
        this.y=2;
        int count = 0;
        this.setLayout(new GridLayout(x,y));
        for(int i=0;i<x;i++){

            for(int j=0;j<y;j++){
                Square square=new BaseSquare(-1);
                square.setPreferredSize(dimension);
                Piece piece=new Piece(icon,player.pawnList.get(count),home.size()); //home.size() es el tamaño del ArrayList<Square> homeGrid del tablero;
                square.add(piece);
                this.add(square);
                home.add(square);
                count++;
            }
        }
    }

}
