package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideBar extends JPanel {
    public JLabel turn = new JLabel("TURN: BLUE");
    public JButton btnRollDice = new JButton("ROLL DICE");
    Dice diceContainer = new Dice();

    public SideBar() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(turn);
        this.add(btnRollDice);
        this.add(diceContainer);
        btnRollDice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Random random=new Random();
                //  int roll=random.nextInt(6-1)+1;
              //  int roll = Board.game.currentRoll;
              //  if(roll != 6){
              //      Piece.advanceGame();
             //       turn.setText("TURN: " + Board.game.getCurrentPlayer().getColor().toUpperCase());
             //   }

                Board.game.currentRoll = Board.game.getCurrentPlayer().rollDice();
                diceContainer.setRoll(Board.game.currentRoll);
                Thread thread = new Thread(diceContainer);
                thread.start();




            }
        });

    }

}
