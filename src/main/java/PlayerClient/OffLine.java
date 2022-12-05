package PlayerClient;

import GUI.Board;

import javax.swing.*;

public class OffLine {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ludo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game game=new Game();
        Board board=new Board(game);
        //  board.addListener();


        frame.add(board);




        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }












}
