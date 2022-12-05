package GUI;

import PlayerClient.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Piece extends JLabel implements Serializable {
    Pawn pawn;
    Component source;
    int index; //Posicion del panel en la base guardada cuando se crea un Piece en la clase grid(Home size);

    public Piece(String imgUrl, Pawn pawn, int index) {
        this.pawn = pawn;
        this.index = index;
        try {
            File icon = new File(imgUrl);
            BufferedImage im = ImageIO.read(icon);
            this.setIcon(new ImageIcon(im));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println(Board.game.turn+" is supposed to be playing");
                if (Board.game.turn == pawn.getPlayer().getTurn()) {
                    //  System.out.println(pawn.getPosition() + " ");
                    //   movePawn(pawn.getPlayer().path, Board.game.currentRoll);
                    //    source = mouseEvent.getComponent();
                    // System.out.println(pawn.getPlayer().getTurn());
                    if (pawn.getPlayer().getPawnList().get(0) == pawn) {
                        movePawn(pawn.getPlayer().path, Board.game.currentRoll);
                    } else if (pawn.getPlayer().getPawnList().get(1) == pawn) {

                        movePawn(pawn.getPlayer().path, Board.game.currentRoll);
                    } else if (pawn.getPlayer().getPawnList().get(2) == pawn) {

                        movePawn(pawn.getPlayer().path, Board.game.currentRoll);
                    } else if (pawn.getPlayer().getPawnList().get(3) == pawn) {

                        movePawn(pawn.getPlayer().path, Board.game.currentRoll);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

    }

    public void movePawn(Path path, int roll) {
        if (roll == 6 && pawn.getPosition() == -1) {
            moveOutTheBase(path);
            pawn.squaresMoved+=1;
        } else if (pawn.getPosition() != -1) {
            // moveAndValidateEnemyPlayerOnPath(path, roll);
            moveOnPath(path, roll);
            pawn.squaresMoved+=roll;
        } else if(Board.game.currentRoll!=6){
            advanceGame();//////////////////////////////////////////

        }
    }

    public void advanceGame() {


        if (Board.game.turn < 4) {
            Board.game.turn++;
        } else if (Board.game.turn == 4) {
            Board.game.turn = 1;
        }
       // int turn = Board.game.getTurn();
        Board.sideBar.turn.setText(Board.game.getCurrentPlayer().color + "'s turn");


          if(Board.game.getCurrentPlayer().getTurn()==Board.game.turn){//send the updated state
            System.out.println("Sending updated state to server from advanceGame");

            try {
                Mapper broadcast=new Mapper("PLAY",Board.game);
                Board.mp.sender.writeObject(broadcast);

                Mapper grid=new Mapper("GRID",Board.grid);
                Board.mp.sender.writeObject(grid);





                Board.mp.sender.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    private void moveOutTheBase(Path path) { //Metodo para salir de base al la posicion inicial;
        int x = Board.game.getTurn();////////////////
        Board.grid.get(path.startingPoing.pos).add(this);
     //   Board.grid.get(path.startingPoing.pos).revalidate();              <....................................
      //  Board.grid.get(path.startingPoing.pos).repaint();                 <....................................


     //   Board.homeGrid.get(this.index).repaint();                         <....................................
     //   Board.homeGrid.get(this.index).revalidate();                      <....................................

        pawn.setPosition(path.startingPoing.pos);



    }




    private void moveOnPath(Path path, int roll) {
        if(pawn.squaresMoved+roll<=57){
            int x = Board.game.getTurn();/////////
            int oldPosition = pawn.getPosition();
            Node current = path.startingPoing;
            while (current.pos != oldPosition) {
                current = current.next;
            }
            for (int i = 0; i < roll; i++) {
                current = current.next;
            }
            //VERIFICA SI HAY UNA PIEZA ENEMIGA EN LA POSICION
            if (Board.grid.get(current.pos).getComponentCount() > 0) {
                moveAndValidateEnemyPlayerOnPath(path, roll, current, oldPosition);
            } else if (Board.grid.get(current.pos).getComponentCount() == 0) {
                moveAndCheckRollSixOrNot(path, roll, current, oldPosition);
            }
        }else{
            String player = Board.game.getCurrentPlayer().color;

            if (player.equals("red")) {
                Board.grid.get(pawn.getPosition()).remove(this);
                Board.grid.get(pawn.getPosition()).revalidate();
                Board.grid.get(pawn.getPosition()).repaint();
                pawn.setPosition(43);
                Board.grid.get(43).add(this);
                Board.grid.get(43).revalidate();
                Board.grid.get(43).repaint();
                advanceGame();
            } else if (player.equals("blue")) {
                Board.grid.get(pawn.getPosition()).remove(this);
                Board.grid.get(pawn.getPosition()).revalidate();
                Board.grid.get(pawn.getPosition()).repaint();
                pawn.setPosition(39);
                Board.grid.get(39).add(this);
                Board.grid.get(39).revalidate();
                Board.grid.get(39).repaint();
                advanceGame();
            } else if (player.equals("green")) {
                Board.grid.get(pawn.getPosition()).remove(this);
                Board.grid.get(pawn.getPosition()).revalidate();
                Board.grid.get(pawn.getPosition()).repaint();
                pawn.setPosition(41);
                Board.grid.get(41).add(this);
                Board.grid.get(41).revalidate();
                Board.grid.get(41).repaint();
                advanceGame();
            } else if (player.equals("yellow")) {
                Board.grid.get(pawn.getPosition()).remove(this);
                Board.grid.get(pawn.getPosition()).revalidate();
                Board.grid.get(pawn.getPosition()).repaint();
                pawn.setPosition(37);
                Board.grid.get(37).add(this);
                Board.grid.get(37).revalidate();
                Board.grid.get(37).repaint();
                advanceGame();
            }

        }

    }

    private void moveAndValidateEnemyPlayerOnPath(Path path, int roll, Node current, int oldPosition) {
        int x = Board.game.getTurn();/////////
        Piece piece = (Piece) Board.grid.get(current.pos).getComponent(0);
        if (!piece.getPawn().getPlayer().equals(pawn.getPlayer())) {
            //System.out.println("ENEMIGO EN LA POSICION");
            Board.grid.get(current.pos).remove(piece); //Elimina la pieza enemiga
         //   Board.grid.get(current.pos).repaint(); //Repinta el panel                  <....................................
         //   Board.grid.get(current.pos).revalidate(); //Revalida el panel               <....................................

            Board.homeGrid.get(piece.index).add(piece); //Agrega la pieza enemiga a la base
          //  Board.homeGrid.get(piece.index).repaint();//Repinta el panel                <....................................
          //  Board.homeGrid.get(piece.index).revalidate(); //Revalida el panel           <....................................
            piece.getPawn().setPosition(-1);//Actualiza la posicion de la pieza enemiga


            Board.grid.get(oldPosition).remove(this); //Elimina la pieza del jugador
         //   Board.grid.get(oldPosition).repaint(); //Repinta el panel                   <....................................
          //  Board.grid.get(oldPosition).revalidate(); //Revalida el panel                <....................................

            Board.grid.get(current.pos).add(this); //Agrega la pieza del jugador a la posicion
          //  Board.grid.get(current.pos).repaint(); //Repinta el panel                   <....................................
         //   Board.grid.get(current.pos).revalidate(); //Revalida el panel               <....................................

            pawn.setPosition(current.pos); //Actualiza la posicion de la pieza del jugador1
            if (roll != 6) {
                advanceGame();

            }

        } else {
            //System.out.println("AMIGO EN LA POSICION");


            Board.grid.get(oldPosition).remove(this); //Elimina la pieza del jugador
         //   Board.grid.get(oldPosition).repaint(); //Repinta el panel           <....................................
         //   Board.grid.get(oldPosition).revalidate(); //Revalida el panel       <....................................

              Board.grid.get(current.pos).add(this); //Agrega la pieza del jugador a la posicion
        //    Board.grid.get(current.pos).repaint(); //Repinta el panel           <....................................
         //   Board.grid.get(current.pos).revalidate(); //Revalida el panel       <....................................

            pawn.setPosition(current.pos); //Actualiza la posicion de la pieza del jugador
            if (roll != 6) {
                advanceGame();

            }

        }
    }

    public void moveAndCheckRollSixOrNot(Path path, int roll, Node current, int oldPosition) {
        int x = Board.game.getTurn();/////////
        Board.grid.get(oldPosition).remove(this); //Elimina la pieza del jugador
       // Board.grid.get(oldPosition).repaint(); //Repinta el panel                   <....................................
     //   Board.grid.get(oldPosition).revalidate(); //Revalida el panel               <....................................

        Board.grid.get(current.pos).add(this); //Agrega la pieza del jugador a la posicion
      //  Board.grid.get(current.pos).repaint(); //Repinta el panel                   <....................................
      //  Board.grid.get(current.pos).revalidate(); //Revalida el panel               <....................................

        pawn.setPosition(current.pos); //Actualiza la posicion de la pieza del jugador
        if (roll != 6) {
           advanceGame();

        }


    }


}
