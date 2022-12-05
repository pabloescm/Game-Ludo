package GUI;

import PlayerClient.Initializer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Dice extends JLabel implements Runnable {
    int roll;
    public Dice(){
        File icon=new File(Initializer.d1);
        try {
            BufferedImage im= ImageIO.read(icon);
            this.setIcon(new ImageIcon(im));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    @Override
    public void run() {
        rollDiceAction();
        setFinalRoll();
    }
    public void rollDiceAction(){

        String[] actions=new String[]{Initializer.d1,Initializer.d2,Initializer.d3,Initializer.d4,Initializer.d5,Initializer.d5,Initializer.d6};
        for(int i=0;i<actions.length;i++){
            File icon=new File(actions[i]);
            try {
                BufferedImage im= ImageIO.read(icon);
                this.setIcon(new ImageIcon(im));
                this.revalidate();
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void setFinalRoll(){
        File icon;
        switch (roll){

            case 1:
                icon=new File(Initializer.d1);
                try {
                    BufferedImage im= ImageIO.read(icon);
                    this.setIcon(new ImageIcon(im));
                    this.revalidate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                icon=new File(Initializer.d2);
                try {
                    BufferedImage im= ImageIO.read(icon);
                    this.setIcon(new ImageIcon(im));
                    this.revalidate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                icon=new File(Initializer.d3);
                try {
                    BufferedImage im= ImageIO.read(icon);
                    this.setIcon(new ImageIcon(im));
                    this.revalidate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                icon=new File(Initializer.d4);
                try {
                    BufferedImage im= ImageIO.read(icon);
                    this.setIcon(new ImageIcon(im));
                    this.revalidate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break ;
            case 5:
                icon=new File(Initializer.d5);
                try {
                    BufferedImage im= ImageIO.read(icon);
                    this.setIcon(new ImageIcon(im));
                    this.revalidate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                icon=new File(Initializer.d6);
                try {
                    BufferedImage im= ImageIO.read(icon);
                    this.setIcon(new ImageIcon(im));
                    this.revalidate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
