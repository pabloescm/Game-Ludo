package GUI;

import javax.swing.*;
import java.awt.*;

public class Container extends JPanel {

    public Container(Color color){
        this.setLayout(new GridBagLayout());
        this.setBackground(color);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
