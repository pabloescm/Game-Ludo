package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Square extends JPanel implements Serializable {
    int index;
    public Square(int index){
        this.index=index;
        this.setSize(new Dimension(75,75));
        this.setBorder(BorderFactory.createLineBorder(Color.black));


    }

    public void setColor(Color color){
        this.setBackground(color);
    }


}
