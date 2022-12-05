package GUI;

import PlayerClient.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

public class Board extends JPanel implements PropertyChangeListener,Runnable {
    public static MainPlayer mp;
    public static Game game;
    public static ArrayList<Square> grid = new ArrayList<>();
    public static ArrayList<Square> homeGrid = new ArrayList<>();
    static int counter = 0;
    Container blueBase = new Container(Color.BLUE);//jPanel
    Container yellowBase = new Container(Color.YELLOW);//jPanel
    Container redBase = new Container(Color.RED);//jPanel
    Container greenBase = new Container(Color.GREEN);//jPanel
    Color blueColor = new Color(74, 108, 189, 255);
    Color yellowColor = new Color(145, 139, 9, 224);

    Color greenColor = new Color(13, 112, 36, 184);

    Color redColor = new Color(168, 36, 43, 136);

    //adding the sidebar panel for game control
    static SideBar sideBar = new SideBar();

    public Board(Game game) {
        this.game = game;

        //this.setSize(new Dimension(500,500));
        this.setLayout(new GridLayout(4, 3));
        initBoard();
        initBases();

        this.add(sideBar);
        initColors();

        game.addPropertyChangeListener(this);

    }
    public void initColors(){
        initBluePlayerColor();
        initYellowPlayerColor();
        initGreenPlayerColor();
        initRedPlayerColor();
        initHomeBases();
    }

    public MainPlayer getMp() {
        return mp;
    }

    public void setMp(MainPlayer mp) {
        this.mp = mp;
    }

    public void initBoard() {
        this.add(blueBase);
        this.add(new Grid(6, 3));
        this.add(yellowBase);
        this.add(new Grid(3, 6));
        this.add(new Grid(3, 3));//center grid
        this.add(new Grid(3, 6));
        this.add(redBase);
        this.add(new Grid(6, 3));
        this.add(greenBase);
    }

    public void initBases() {
        blueBase.add(new Grid(Initializer.blue1, Board.game.blue, homeGrid));
        yellowBase.add(new Grid(Initializer.yellow1, Board.game.yellow, homeGrid));
        redBase.add(new Grid(Initializer.red1, Board.game.red, homeGrid));
        greenBase.add(new Grid(Initializer.green1, Board.game.green, homeGrid));
    }

    private void initBluePlayerColor() {
        Node current = game.blue.path.startingPoing;
        for (int i = 0; i < Initializer.bluePath.length; i++) {

            if (i == 0 || i == 51 || i == 52 || i == 53 || i == 54 || i == 55) {
                grid.get(current.pos).setColor(blueColor);
            }
            current = current.next;


        }
    }

    private void initYellowPlayerColor() {
        Node current = game.yellow.path.startingPoing;
        for (int i = 0; i < Initializer.yellowPath.length; i++) {

            if (i == 0 || i == 51 || i == 52 || i == 53 || i == 54 || i == 55) {
                grid.get(current.pos).setColor(yellowColor);
            }
            current = current.next;
        }
    }

    private void initRedPlayerColor() {
        Node current = game.red.path.startingPoing;
        for (int i = 0; i < Initializer.redPath.length; i++) {

            if (i == 0 || i == 51 || i == 52 || i == 53 || i == 54 || i == 55) {
                grid.get(current.pos).setColor(redColor);
            }
            current = current.next;
        }
    }

    private void initGreenPlayerColor() {
        Node current = game.green.path.startingPoing;
        for (int i = 0; i < Initializer.greenPath.length; i++) {

            if (i == 0 || i == 51 || i == 52 || i == 53 || i == 54 || i == 55) {
                grid.get(current.pos).setColor(greenColor);
            }
            current = current.next;
        }
    }
    private void initHomeBases(){
        initBlueBase();
        initYellowBase();
        initRedBase();
        initGreenBase();
    }

    private void initBlueBase() {
        String baseName = "HOME";
        Font font = new Font("Arial", Font.BOLD, 20);
        JLabel label = new JLabel(baseName);
        label.setFont(font);

        JPanel panelHome = grid.get(39);
        panelHome.setBackground(blueColor);
        panelHome.add(label);
    }

    private void initYellowBase() {
        String baseName = "HOME";
        Font font = new Font("Arial", Font.BOLD, 20);
        JLabel label = new JLabel(baseName);
        label.setFont(font);

        JPanel panelHome = grid.get(37);
        panelHome.setBackground(yellowColor);
        panelHome.add(label);
    }
    private void initRedBase() {
        String baseName = "HOME";
        Font font = new Font("Arial", Font.BOLD, 20);
        JLabel label = new JLabel(baseName);
        label.setFont(font);

        JPanel panelHome = grid.get(43);
        panelHome.setBackground(redColor);
        panelHome.add(label);
    }

    private void initGreenBase() {
        String baseName = "HOME";
        Font font = new Font("Arial", Font.BOLD, 20);
        JLabel label = new JLabel(baseName);
        label.setFont(font);

        JPanel panelHome = grid.get(41);
        panelHome.setBackground(greenColor);
        panelHome.add(label);
    }




    public void addListener() {
        game.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("property changed");
        repaint();

        for (int i = 0; i < grid.size(); i++) {
            grid.get(i).repaint();
            grid.get(i).revalidate();
        }
        sideBar.repaint();
        sideBar.revalidate();

    /*
     int turn = game.turn;

        if (turn == 1) {
          //  sideBar.turn.setText("Blue");
            Board.sideBar.turn.setText(Board.game.getCurrentPlayer().color + "'s turn");
        } else if (turn == 2) {
           // sideBar.turn.setText("Yellow");
            Board.sideBar.turn.setText(Board.game.getCurrentPlayer().color + "'s turn");
        } else if (turn == 3) {
            //sideBar.turn.setText("Red");
            Board.sideBar.turn.setText(Board.game.getCurrentPlayer().color + "'s turn");
        } else if (turn == 4) {
            //sideBar.turn.setText("Green");
            Board.sideBar.turn.setText(Board.game.getCurrentPlayer().color + "'s turn");
        }
     */

    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("OLD GAME "+Game.serialVersionUID);
                Mapper mapper=(Mapper)mp.receiver.readObject();
                if(mapper.getKey().equals("PLAY")) {
                    int change= game.getTurn();
                    game = (Game) mapper.getValue();
                   // JOptionPane.showMessageDialog(this,game.turn);
                    sideBar.turn.setText(String.valueOf(game.getTurn()));
                    System.out.println("NEW GAME RECEIVED "+Game.serialVersionUID);
                    System.out.println("TURN====="+game.turn);
                }else if(mapper.getKey().equals("GRID")){
                    grid = (ArrayList<Square> )mapper.getValue();
                    /*
                   ArrayList<Square> received=(ArrayList<Square>) mapper.getValue();
                   grid.clear();
                   this.revalidate();
                   this.repaint();
                    Collections.copy(received,grid);
                        /* for (int i = 0; i < grid.size(); i++) {
                        if (grid.get(i).getComponentCount()>0) {
                            JLabel label = (JLabel) grid.get(i).getComponent(0);
                            grid.get(i).add(label);
                            label.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\ludo\\src\\main\\resources\\images\\blue1.png"));
                            System.out.println("In the position "+i+" there is "+grid.get(i).getComponentCount()+" componentes");
                            grid.get(i).repaint();
                            grid.get(i).revalidate();
                            System.out.println("making changes in the grid");


                        }*/
                    for (int i = 0; i < this.grid.size(); i++) {
                             grid.get(i).repaint();
                            grid.get(i).revalidate();
                        System.out.println("repainting the grid");

                        }

                    }



               game.getTurn();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
          //  } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
